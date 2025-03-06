package com.nd_monitoring.respository;
import com.nd_monitoring.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsersRepository extends JpaRepository <UsersEntity, Long> {
    boolean existsByUserid(String userid);

    UsersEntity findByUserid(String userid);
}
