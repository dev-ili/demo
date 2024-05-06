package com.example.demo.first.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.first.action.MemberAction;
import com.example.demo.first.dto.MemberDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@CrossOrigin(origins = { "http://localhost:3000" }, allowCredentials = "true")
@RestController
public class MemberController {
    @Autowired
    private MemberAction memberAction;
    
    /**
     * @param dto
     * @return
     */
    @PostMapping("/member/signup")
    public String signup(@RequestBody MemberDto dto) {
        JSONObject result = new JSONObject();

        int errCode = 0;
        String errMsg = "";
        try {
            System.out.println(dto);
            JSONObject data = memberAction.signup(dto);
            result.put("data", data);

        } catch (Exception e) {
            errCode = -1;
            errMsg = e.getMessage();
            e.printStackTrace();
        }

        result.put("errCode", errCode);
        result.put("errMsg", errMsg);

        return result.toString();
    }

    /**
     * @param dto
     * @param request
     * @return
     */
    @PostMapping("/member/login")
    public String login(@RequestBody MemberDto dto, HttpServletRequest request) {
        JSONObject result = new JSONObject();

        int errCode = 0;
        String errMsg = "";

        try {
            HttpSession session = request.getSession();
            JSONObject data = memberAction.login(session, dto);
            result.put("data", data);
        } catch (Exception e) {
            errCode = -1;
            errMsg = e.getMessage();
            e.printStackTrace();
        }
        
        result.put("errCode", errCode);
        result.put("errMsg", errMsg);
        
        return result.toString();
    }
    
    @RequestMapping("/member/logout")
    public String logout(HttpServletRequest request) {
        JSONObject result = new JSONObject();

        int errCode = 0;
        String errMsg = "";
        try {
            HttpSession session = request.getSession();
            if(session.getAttribute("loginYn") != null && (boolean) session.getAttribute("loginYn")) {
                session.invalidate();
            }
        } catch (Exception e) {
            errCode = -1;
            errMsg = e.getMessage();
            e.printStackTrace();
        }

        result.put("errCode", errCode);
        result.put("errMsg", errMsg);
        
        return result.toString();
    }
    
}