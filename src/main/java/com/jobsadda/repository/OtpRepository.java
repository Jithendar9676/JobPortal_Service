package com.jobsadda.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jobsadda.entity.Otp;

@Repository
public interface OtpRepository extends JpaRepository<Otp,String>{

	
	ArrayList<Otp> findByCreatedOnBefore(LocalDateTime expiry);

}
