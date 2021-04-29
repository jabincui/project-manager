package com.buptcnc.projectmanager.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.buptcnc.projectmanager.model.DocTemplate;
import com.buptcnc.projectmanager.service.DocTemplateServiceImpl;
import com.buptcnc.projectmanager.service.ProfServiceImpl;
import com.buptcnc.projectmanager.service.ProjectServiceImpl;
import com.buptcnc.projectmanager.util.DailyToken;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class PageController {

    private static final String FAIL = "FAIL";
    private static final String EXIST = "EXIST";

    @Autowired
    private ProjectServiceImpl projectService;
    @Autowired
    private DocTemplateServiceImpl docTemplateService;
    @Autowired
    private ProfServiceImpl profService;


    /**
     * 测试时用，发布时删除
     * @return
     */
//    @GetMapping(value = "/")
//    public String testHome() {
//        return "prof_index";
//    }


    @GetMapping(value = "/signin")
    public String signIn() {
        return "sign_in";
    }

    @GetMapping(value = "/signup")
    public String signup() {
        return "signup";
    }

    @GetMapping(value = "/prof_index")
    public String profIndex() {
        return "prof_index";
    }

    @GetMapping(value = "/new_proj")
    public String profNewProj() {
        return "prof_index_new_proj";
    }

    @GetMapping(value = "/my_proj")
    public String profMyProj() {
        return "prof_index_my_proj";
    }

    @GetMapping(value = "/my_doc_template")
    public String profDocTemplate(@RequestParam String profId, Model model) {
        QueryWrapper<DocTemplate> dtqw = new QueryWrapper<>();
        dtqw.eq("prof_id", profId);
        model.addAttribute("docTemplates", docTemplateService.list(dtqw));
        model.addAttribute("now", new Date());
        model.addAttribute("format", new SimpleDateFormat("yyyy-MM-dd"));
        return "prof_index_doc_template";
    }

    @GetMapping(value = "/prof_proj")
    public String profProj(@RequestParam String projId, Model model) {
        model.addAttribute("proj", projectService.getById(projId));
        model.addAttribute("format", new SimpleDateFormat("yyy-MM-dd"));
        // 不讲五德实现id2name
        model.addAttribute("profService", profService);
        model.addAttribute("id2name", new Id2Name());
        return "prof_index_proj";
    }

    @GetMapping("/new_doc_template")
    public String profNewDocTemplate() {
        return "prof_index_new_doc_template";
    }


    /**
     * 进入signup发送一个signupToken
     * @param request
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/register_prof")
    public String registerProf(HttpServletRequest request) {
        // token检查
        String token = request.getHeader("signupToken");
        if (token == null || "".equals(token)) return FAIL;
        if (!token.equals(DailyToken.getToken())) return FAIL;
        // 检查通过
        String account = request.getHeader("account");
        String password = request.getHeader("password");
        // prof account exist?
        if (false) return EXIST;

        // register in t_prof

        // register in t_user

        // register in t_role

        return "";
    }


    /**
     * 不讲五德的替代方式
     */
    @Data
    class Id2Name {
        @Autowired
        private ProfServiceImpl profService;

        public String getName(String id) {
            return profService.getById(id).getName();
        }
    }

}
