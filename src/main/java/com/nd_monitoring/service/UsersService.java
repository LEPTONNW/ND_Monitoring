package com.nd_monitoring.service;

import com.nd_monitoring.dto.UsersDTO;
import org.springframework.stereotype.Service;

@Service
public interface UsersService {
    String createuser(UsersDTO usersDTO);

    UsersDTO getuserinfo(String userid);
}
