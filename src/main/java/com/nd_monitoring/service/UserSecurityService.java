package com.nd_monitoring.service;

import com.nd_monitoring.CustomException;
import com.nd_monitoring.entity.UsersEntity;
import com.nd_monitoring.respository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserSecurityService implements UserDetailsService {
    private final UsersRepository usersRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    //로그인 처리
    @Override
    public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
        try {
            UsersEntity usersEntity = this.usersRepository.findByUserid(userid);

            if (usersEntity == null) {
                throw new CustomException("사용자를 찾을 수 없습니다.");
            }

            // 권한 설정
            List<GrantedAuthority> authorityList = new ArrayList<>();
            String permission = usersEntity.getPermission();

            if (permission == null) {
                throw new CustomException("사용자의 권한 정보가 누락되었습니다.");
            }

            switch (permission) {
                case "USER":
                    authorityList.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
                    break;
                case "ADMIN":
                    authorityList.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
                    break;
                default:
                    throw new CustomException("알 수 없는 권한입니다: " + permission);
            }

            // UserDetails 반환
            return new User(usersEntity.getUserid(), usersEntity.getPass(), authorityList);

        } catch (Exception e) {
            log.info("LOG!!! : " + e.getMessage());
            throw new CustomException("사용자를 찾을 수 없거나 비밀번호가 일치하지 않습니다.");
        }
    }


}
