package com.nd_monitoring.controller;


import com.nd_monitoring.dto.UsersDTO;
import com.nd_monitoring.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApiController {
    private final UsersService usersService;

    //사용자 생성 관련 시작점
    @PostMapping("/createuser")
    public ResponseEntity<Map<String, String>> CreateUsers(@RequestBody UsersDTO usersDTO) {
        try {
            // DTO 가공
            UsersDTO usersDTO1 = new UsersDTO();
            usersDTO1.setUserid(usersDTO.getUserid());
            usersDTO1.setPass(usersDTO.getPass());
            usersDTO1.setName(usersDTO.getName());
            usersDTO1.setCompany(usersDTO.getCompany());
            usersDTO1.setNotice(usersDTO.getNotice());
            usersDTO1.setPermission(usersDTO.getPermission());

            // 서비스 호출
            String msg = usersService.createuser(usersDTO1);

            if(msg.equals("등록성공")) {
                // JSON 응답으로 변환
                Map<String, String> response = new HashMap<>();
                response.put("message", msg);
                return ResponseEntity.ok(response);  // JSON 응답 반환
            }
            else {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("error2", "Error: 이미 등록된 계정입니다.");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
            }
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
    //사용자 생성 관련 끝점


    //레이아웃 REST요청 시작점
    @GetMapping("/load_layout")
    public ResponseEntity<?> load_layout(@RequestParam String userid) {
        try {
            UsersDTO usersDTO1 = usersService.getuserinfo(userid);
            log.info("LOG!!! : " + usersDTO1);
            return ResponseEntity.ok(usersDTO1);
        }
        catch (Exception ea) {
            log.info("LOG!!! : " + ea.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ea.getMessage());
        }
    }
    //레이아웃 REST요청 끝점


    @GetMapping("/userlist")
    public Page<UsersDTO> getuserList(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size,
                                      @RequestParam(defaultValue = "", required = false)String searchType,
                                      @RequestParam(defaultValue = "", required = false)String keyword){
        return usersService.getuserList(page,size,searchType,keyword);
    }

}
