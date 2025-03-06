package com.nd_monitoring.service;

import com.nd_monitoring.CustomException;
import com.nd_monitoring.dto.UsersDTO;
import com.nd_monitoring.entity.UsersEntity;
import com.nd_monitoring.respository.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
@RequiredArgsConstructor
@Log4j2
@Transactional
public class UsersServiceImpl implements UsersService {
    private final PasswordEncoder passwordEncoder;
    private final UsersRepository usersRepository;
    private final ModelMapper modelMapper = new ModelMapper();


    @Override
    public String createuser(UsersDTO usersDTO) {
        try {
            boolean tf = usersRepository.existsByUserid(usersDTO.getUserid());

            if(tf) {
                return "등록실패";
            }

            else {
                String temp = usersDTO.getPass();
                usersDTO.setPass(passwordEncoder.encode(temp)); //패스워드 암호화

                String permission = usersDTO.getPermission();
                if(permission.equals("manager")) {
                    usersDTO.setPermission("ADMIN");
                }
                else {
                    usersDTO.setPermission("USER");
                }

                UsersEntity usersEntity = modelMapper.map(usersDTO, UsersEntity.class);

                usersRepository.save(usersEntity);
                return "등록성공";
            }
        }
        catch (Exception e) {
            throw new CustomException("Error : 등록실패");
        }
    }

    @Override
    public UsersDTO getuserinfo(String userid) {
        try {
            UsersEntity usersEntity = usersRepository.findByUserid(userid);
            UsersDTO usersDTO = new UsersDTO();
            usersDTO.setName(usersEntity.getName());
            usersDTO.setCompany(usersEntity.getCompany());

            return usersDTO;
        }
        catch (Exception ea) {
            throw new CustomException("사용자를 찾을 수 없습니다.");
        }
    }
}
