package com.buptcnc.projectmanager.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.buptcnc.projectmanager.config.Constant;
import com.buptcnc.projectmanager.model.*;
import com.buptcnc.projectmanager.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/mana")
public class ManagerController {

    @Autowired
    ManagerService managerService;
    @Autowired
    StuServiceImpl stuService;
    @Autowired
    CodeManageHistoryServiceImpl cmhService;
    @Autowired
    DocManageHistoryServiceImpl dmhService;


    @PostMapping("/show_stu")
    public List<Stu> showStus(@RequestBody Project project) {
        if (project.getId() == null) return null;
        QueryWrapper<Manager> mwq = new QueryWrapper<>();
        mwq.eq("proj_id", project.getId());
        List<Manager> managers = managerService.list(mwq);
        QueryWrapper<Stu> swq = new QueryWrapper<>();
        for (Manager manager: managers) {
            swq.ne("account", manager.getStuId());
        }
        return stuService.list(swq);
    }

    /**
     * 把学生加入项目
     * 任何一个参数为空则返回错误，但出错前的条目会正常执行
     */
    @PostMapping("/add_stu")
    public String addStus(@RequestBody Manager[] managers) {
        QueryWrapper<Manager> mqw;
        for (Manager manager: managers) {
            if (manager.getStuId() == null || manager.getProjId() == null)
                return Constant.INVALID;
            mqw = new QueryWrapper<>();
            mqw.eq("stu_id", manager.getStuId())
                    .eq("proj_id", manager.getProjId());
            if (managerService.getOne(mqw) == null)
                continue;
            managerService.save(manager);
        }
        return Constant.PASS;
    }

    @GetMapping("my_code_task")
    public List<CodeManageHistory> getMyCodeTasks(@RequestBody Manager manager) {
        if (manager.getStuId() == null) return null;
        QueryWrapper<CodeManageHistory> cmhqw = new QueryWrapper<>();
        cmhqw.eq("manager_id", manager.getId()).isNull("end");
        return cmhService.list(cmhqw);
    }

    @GetMapping("my_doc_task")
    public List<DocManageHistory> getMyDocTasks(@RequestBody Manager manager) {
        if (manager.getStuId() == null) return null;
        QueryWrapper<DocManageHistory> dmhqw = new QueryWrapper<>();
        dmhqw.eq("manager_id", manager.getId()).isNull("end");
        return dmhService.list(dmhqw);
    }

//    /**
//     * 判断学生是否可以退出项目
//     * @param manager
//     * @return
//     */
//    @PostMapping("/can_exit_proj")
//    public boolean canExit(@RequestBody Manager manager,
//                           @RequestParam String projId) {
//        if (manager.getStuId() == null) return false;
//        QueryWrapper<CodeManageHistory> cmhqw = new QueryWrapper<>();
//        cmhqw.eq("manager_id", manager.getId()).isNull("end");
//
//        if (cmhService.list(cmhqw).size() > 0)
//            return false;
//    }

    /**
     * 判断学生是否有未完项目（毕业前检查）
     * @param manager
     * @return
     */
    @PostMapping("/can_exit")
    public boolean canExit(@RequestBody Manager manager) {
        if (manager.getStuId() == null) return false;
        QueryWrapper<CodeManageHistory> cmhqw = new QueryWrapper<>();
        cmhqw.eq("manager_id", manager.getId()).isNull("end");
        if (cmhService.list(cmhqw).size() > 0) return false;
        QueryWrapper<DocManageHistory> dmhqw = new QueryWrapper<>();
        dmhqw.eq("manager_id", manager.getId()).isNull("end");
        if (dmhService.list(dmhqw).size() > 0) return false;
        return true;
    }
}
