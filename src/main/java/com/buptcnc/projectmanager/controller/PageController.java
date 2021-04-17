package com.buptcnc.projectmanager.controller;

import com.buptcnc.projectmanager.util.DailyToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PageController {

    private static final String FAIL = "FAIL";
    private static final String EXIST = "EXIST";


    /**
     * 测试时用，发布时删除
     * @return
     */
    @GetMapping(value = "/")
    public String testHome() {
        return "prof_index";
    }


    @GetMapping(value = "/signin")
    public String signIn() {
        return "sign_in";
    }

    @GetMapping(value = "/signup")
    public String signup() {
        return "signup";
    }

    @GetMapping(value = "/prof_index")
    public String index() {
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

    @GetMapping(value = "/doc_template")
    public String profDocTemplate() {
        return "prof_index_doc_template";
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

}
