package com.example.demo.first.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.first.dto.MemberDto;

@Mapper
public interface MemberDao {

    int insertMemberByDto(MemberDto dto) throws Exception;

    MemberDto selectMemberByDto(MemberDto dto) throws Exception;

    MemberDto selectMemberById(String id) throws Exception;

    int updateMemberByDto(MemberDto dto) throws Exception;

    MemberDto selectMemberByRegistrationNumber(int registrationNumber) throws Exception;
    
}
