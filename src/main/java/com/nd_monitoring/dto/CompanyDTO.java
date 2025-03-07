package com.nd_monitoring.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class CompanyDTO {
    //ID 순번
    private Long mno;

    //상호명
    private String company_name;

    //관리자(담당자) 이름
    private String sv_name;

    //분사, 열선, 기타 장치여부
    private String device;

    //활성 여부
    private String active;

    //등록일시
    private LocalDate regdate;
}
