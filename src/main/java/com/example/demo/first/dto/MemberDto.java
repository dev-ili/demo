package com.example.demo.first.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MemberDto {
    private int memberRegistrationNumber;
    private String id;
    private String password;
    private String name;
    private String nickname;
    private String email;
    private boolean useYn;
    private String createDate;
    private String deleteDate;
    private String lastConnectDatetime;
}
