package com.nd_monitoring.respository;
import com.nd_monitoring.entity.UsersEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UsersRepository extends JpaRepository <UsersEntity, Long> {
    boolean existsByUserid(String userid);

    UsersEntity findByUserid(String userid);

    Page<UsersEntity>findAll(Pageable pageable);
    Page<UsersEntity> findByNameContaining(String name, Pageable pageable);
    Page<UsersEntity> findByCompanyContaining(String company, Pageable pageable);

}
