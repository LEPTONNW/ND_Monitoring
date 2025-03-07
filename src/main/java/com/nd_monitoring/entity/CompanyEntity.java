package com.nd_monitoring.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(value = {AuditingEntityListener.class})
public class CompanyEntity {
    //ID 순번
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cno;

    //상호명
    @Column
    @NotEmpty(message = "상호명 입력은 필수 입니다.")
    private String company_name;

    //관리자(담당자) 이름
    @Column
    private String sv_name;

    //분사, 열선, 기타 장치 여부
    @Column
    private String device;

    //활성 여부
    @Column
    @NotEmpty(message = "활성 여부는 필수 입니다.")
    private String active;

    //등록일시
    @Column
    @CreatedDate
    private LocalDate regdate;

}
