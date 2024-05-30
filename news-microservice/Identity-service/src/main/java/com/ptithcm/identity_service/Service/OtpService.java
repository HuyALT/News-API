package com.ptithcm.identity_service.Service;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ptithcm.identity_service.entity.UserEntity;
import com.ptithcm.identity_service.manager.OtpManager;
import com.ptithcm.identity_service.repository.UserRepository;


@Service
public class OtpService {
	
	@Autowired
	private UserRepository userRepository;
	
    private static final String OTP_CHARACTERS = "0123456789";
    private static final int OTP_LENGTH = 6;
	
	@Autowired
	private JavaMailSender mailSender;
	
    public boolean sendOtp(String email) {
    	Optional<UserEntity> userEntity = userRepository.findByEmail(email);
    	if (userEntity.isPresent()&&userEntity.get().getActicve()==1) return false;
    	
        String otp = generateOtp(); 
        OtpManager.storeOTP(email, otp);
        
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Mã OTP");
        message.setText("Mã OTP của bạn là: " + otp+" có hiệu lực trong 10 phút");
        mailSender.send(message);
        return true;
    }
	
    public boolean verifyOtp(String email, String otp) {
        String savedOtp = OtpManager.getOTP(email);
        if (savedOtp != null && savedOtp.equals(otp)) {
        	Optional<UserEntity> userEntity = userRepository.findByEmail(email);
        	userEntity.get().setActicve(1);
            OtpManager.removeOTP(email);
            userRepository.save(userEntity.get());
            return true;
        }
        return false;
    }
	
    private String generateOtp() {
        StringBuilder otp = new StringBuilder(OTP_LENGTH);
        Random random = new Random();
        
        for (int i = 0; i < OTP_LENGTH; i++) {
            otp.append(OTP_CHARACTERS.charAt(random.nextInt(OTP_CHARACTERS.length())));
        }
        return otp.toString();
    }
}
