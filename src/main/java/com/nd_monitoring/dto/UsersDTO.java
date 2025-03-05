package com.nd_monitoring.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class UsersDTO {
    //ID 순번
    private Long mno;

    //아이디
    private String userid;

    //패스워드
    private String pass;

    //상호명
    private String company;

    //계정 생성일시
    private LocalDate regdate;
}
