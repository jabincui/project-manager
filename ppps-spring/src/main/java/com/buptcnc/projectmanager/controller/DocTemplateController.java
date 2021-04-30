package com.buptcnc.projectmanager.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.buptcnc.projectmanager.config.Constant;
import com.buptcnc.projectmanager.model.DocTemplate;
import com.buptcnc.projectmanager.service.DocTemplateServiceImpl;
import com.buptcnc.projectmanager.mservice.FileUDLoad;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.wildfly.common.annotation.NotNull;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/doc_template")
public class DocTemplateController {

    @Autowired
    private DocTemplateServiceImpl docTemplateService;

    @GetMapping("/show_all")
    public List<DocTemplate> all() {
        return docTemplateService.list();
    }

    @GetMapping("/my_templates")
    public List<DocTemplate> getMyTemplates(
            @RequestParam @NotNull String projId) {
        QueryWrapper<DocTemplate> dqw = new QueryWrapper<>();
        dqw.eq("proj", projId);
        return docTemplateService.list(dqw);
    }

    /**
     *
     * @param prof
     * @return
     */
    @PostMapping("/upload")
    public String upload(@RequestBody MultipartFile upload,
                          @RequestParam String prof) throws Exception {
        log.info("uploading...");
        String path = "upload/" + prof;
        FileUDLoad fileUDLoad = new FileUDLoad();
        return fileUDLoad.upload(upload, path);
    }

    @GetMapping("/download")
    public void download(@RequestParam String id,
                         HttpServletResponse response) {
        String docPath = docTemplateService.getById(id).getDocPath();
        FileUDLoad fileUDLoad = new FileUDLoad();
        fileUDLoad.download(docPath, response);
    }

    @PostMapping("/add")
    public String insert(@RequestBody DocTemplate docTemplate) {
        if (docTemplate.getType() == null || docTemplate.getExpire() == null
                || docTemplate.getProfId() == null || docTemplate.getDocPath() == null) {
            return Constant.INVALID;
        }
        docTemplateService.save(docTemplate);
        return Constant.PASS;
    }

    @GetMapping("/my_doc_template")
    public List<DocTemplate> getMine(@RequestParam String prof) {
        QueryWrapper<DocTemplate> dtqw = new QueryWrapper<>();
        dtqw.eq("prof_id", prof);
        return docTemplateService.list(dtqw);
    }
}
