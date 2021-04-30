package com.buptcnc.projectmanager.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.buptcnc.projectmanager.config.Constant;
import com.buptcnc.projectmanager.model.Doc;
import com.buptcnc.projectmanager.service.DocServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/doc")
public class DocController {

    @Autowired
    private DocServiceImpl docService;

    @GetMapping("/show_all")
    public List<Doc> all() {
        return docService.list();
    }

    @PostMapping("/add")
    public String publish(@RequestBody Doc doc) {
        if (doc.getName() == null || doc.getProj() == null) return Constant.INVALID;
        QueryWrapper<Doc> dqw = new QueryWrapper<>();
        dqw.eq("name", doc.getName());
        if (docService.getOne(dqw) != null) return Constant.EXIST;
        doc.setState(Constant.STA_PREPARE);
        docService.save(doc);
        return Constant.PASS;
    }


    @PostMapping("/submit")
    public String upload() {
        return "";
    }


}
