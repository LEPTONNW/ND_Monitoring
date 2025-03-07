package com.nd_monitoring.service;

import com.nd_monitoring.dto.UsersDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UsersService {
    String createuser(UsersDTO usersDTO);

    UsersDTO getuserinfo(String userid);

    Page<UsersDTO> getuserList(int page, int size, String searchType, String keyword);
}
