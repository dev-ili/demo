package com.example.demo.first.action;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.first.dao.MemberDao;
import com.example.demo.first.dto.MemberDto;

import jakarta.servlet.http.HttpSession;

@Service
public class MemberActionImpl implements MemberAction {
    @Autowired
    private MemberDao memberDao;

    @Override
    public JSONObject signup(MemberDto dto) throws Exception {
        JSONObject data = new JSONObject();

        int insertRows = 0;
        if (selectMemberById(dto.getId()) == null) {
            insertRows = insertMemberByDto(dto);
        }

        data.put("signupYn", insertRows == 1 ? true : false);

        return data;
    }

    @Override
    public JSONObject login(HttpSession session, MemberDto dto) throws Exception {
        JSONObject data = new JSONObject();

        MemberDto memberDto = selectMemberByDto(dto);
        if (memberDto != null) {
            updateMemberByDto(memberDto);

            JSONObject memberJSON = new JSONObject();
            memberJSON.put("id", memberDto.getId());
            memberJSON.put("name", memberDto.getName());
            memberJSON.put("nickname", memberDto.getNickname());

            session.setAttribute("loginYn", true);
            session.setAttribute("memberDto", memberDto);
            session.setMaxInactiveInterval(1000 * 60 * 10);
            data.put("member", memberJSON);
        }

        data.put("loginYn", memberDto != null ? true : false);

        return data;
    }

    private int updateMemberByDto(MemberDto dto) throws Exception {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        dto.setLastConnectDatetime(now.format(formatter));

        int result = memberDao.updateMemberByDto(dto);
        System.out.println("updated rows : " + result);
        return result;
    }

    public int insertMemberByDto(MemberDto dto) throws Exception {
        int result = memberDao.insertMemberByDto(dto);
        System.out.println("inserted rows : " + result);
        return result;
    }

    public MemberDto selectMemberByDto(MemberDto dto) throws Exception {
        MemberDto result = memberDao.selectMemberByDto(dto);
        System.out.println("selectMemberByDto : " + result);
        return result;
    }

    public MemberDto selectMemberById(String id) throws Exception {
        MemberDto result = memberDao.selectMemberById(id);
        System.out.println("selectMemberById : " + result);
        return result;
    }

    public MemberDto selectMemberByRegistrationNumber(int RegistrationNumber) throws Exception {
        MemberDto result = memberDao.selectMemberByRegistrationNumber(RegistrationNumber);
        System.out.println("selectMemberByRegistrationNumber : " + result);
        return result;
    }

}
