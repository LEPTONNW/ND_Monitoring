package com.nd_monitoring.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(value = {AuditingEntityListener.class})
public class UsersEntity {
    //ID 순번
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uno;

    //아이디
    @Column
    @NotEmpty(message = "아이디 입력은 필수 입니다.")
    private String userid;

    //비밀번호
    @Column
    @NotEmpty(message = "비밀번호 입력은 필수 입니다.")
    private String pass;

    //상호명
    @Column
    @NotEmpty(message = "사업자 명은 필수 입니다.")
    private String company;

    //계정 생성일시
    @Column
    @CreatedDate
    private LocalDate regdate;
}
