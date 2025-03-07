package com.nd_monitoring.respository;
import com.nd_monitoring.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CompanyRepository extends JpaRepository <CompanyEntity, Long> {


}
