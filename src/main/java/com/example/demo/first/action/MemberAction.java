package com.example.demo.first.action;

import org.json.JSONObject;

import com.example.demo.first.dto.MemberDto;

import jakarta.servlet.http.HttpSession;

public interface MemberAction {

    JSONObject signup(MemberDto dto) throws Exception;

    JSONObject login(HttpSession session, MemberDto dto) throws Exception;

}
