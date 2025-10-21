package com.jobsadda.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jobsadda.dto.LogInDTO;
import com.jobsadda.dto.ResponseDTO;
import com.jobsadda.dto.UserRequestDTO;
import com.jobsadda.dto.UserResponeDTO;
import com.jobsadda.entity.Otp;
import com.jobsadda.entity.User;
import com.jobsadda.exception.JobsAddaException;
import com.jobsadda.mapper.UserMapper;
import com.jobsadda.repository.OtpRepository;
import com.jobsadda.repository.UserRepository;
import com.jobsadda.utility.Data;
import com.jobsadda.utility.Utilities;

import jakarta.mail.internet.MimeMessage;
import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertFalse.List;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private OtpRepository otpRepository;
	
	private static final Logger log=LoggerFactory.getLogger(UserServiceImpl.class);
	@Override
	public UserResponeDTO registerUser(UserRequestDTO userRequestDTO) throws JobsAddaException {
		Optional<User> userExist=userRepository.findByEmail(userRequestDTO.getEmail());
		if(userExist.isPresent())throw new JobsAddaException("USER_FOUND");
	      User user=userMapper.toEntity(userRequestDTO);
	      user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
		return userMapper.toDTO(userRepository.save(user));
	}
	@Override
	public UserResponeDTO logInUser(LogInDTO logInDTO) throws JobsAddaException {
		User user=userRepository.findByEmail(logInDTO.getEmail()).orElseThrow(()->new JobsAddaException("LOGIN_FAILED"));
		if(!passwordEncoder.matches(logInDTO.getPassword(), user.getPassword())) throw new JobsAddaException("LOGIN_FAILED");
		return userMapper.toDTO(user);
	}
	@Override
	public Boolean sendOtp(String email) throws Exception {
		User user=userRepository.findByEmail(email).orElseThrow(()->new JobsAddaException("EMAIL_INVALID"));
		MimeMessage mm=mailSender.createMimeMessage();
		MimeMessageHelper message=new MimeMessageHelper(mm,true);
		message.setTo(email);
		message.setSubject("Your OTP Code");
		String genOtp=Utilities.generateOTP();
		Otp otp=new Otp(email,genOtp,LocalDateTime.now());
		otpRepository.save(otp);
		message.setText(Data.getMessageBody(genOtp, user.getName()),true);
		mailSender.send(mm);
		return true;
	
	}
	@Override
	public Boolean verifyOtp(String email, String otp) throws JobsAddaException {
		Otp otpEntiry=otpRepository.findById(email).orElseThrow(()->new JobsAddaException("OTP_NOT_FOUND"));
		if(!otpEntiry.getOtpCode().equals(otp)) {
			throw new JobsAddaException("OTP_INCORRECT");
		}
		if(otpEntiry.getCreatedOn().isBefore(LocalDateTime.now().minusMinutes(5))) {
			throw new JobsAddaException("OTP_NOT_FOUND");
		}
				return true;
	}
	@Override
	public ResponseDTO changePassword(@Valid LogInDTO logInDTO) throws JobsAddaException {
		User user=userRepository.findByEmail(logInDTO.getEmail()).orElseThrow(()->new JobsAddaException("USER_NOT_FOUND"));
		user.setPassword(passwordEncoder.encode(logInDTO.getPassword()));
		userRepository.save(user);
		
		
		return new ResponseDTO("Password changed successfully");
	}
	@Scheduled(fixedRate = 100000)
	public void removeExpiredOtps() {
		LocalDateTime expiry=LocalDateTime.now().minusMinutes(5);
		ArrayList<Otp> expiredOtps=otpRepository.findByCreatedOnBefore(expiry);
		if(!expiredOtps.isEmpty()) {
			otpRepository.deleteAll(expiredOtps);
			log.info("Removed expiredOts :{}" ,expiredOtps.size());
		}
	}
	

}
