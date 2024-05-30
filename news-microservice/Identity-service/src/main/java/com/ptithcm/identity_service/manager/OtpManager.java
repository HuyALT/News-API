package com.ptithcm.identity_service.manager;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class OtpManager {
	private static final long OTP_EXPIRATION_TIME_MS = 10 * 60 * 1000; // 5 minutes
    private static final Map<String, OTPData> otpStorage = new HashMap<>();
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    static {
        scheduler.scheduleAtFixedRate(OtpManager::removeExpiredOTPs, 0, 1, TimeUnit.MINUTES);
    }


    public static void storeOTP(String email, String otp) {
        otpStorage.put(email, new OTPData(otp, System.currentTimeMillis() + OTP_EXPIRATION_TIME_MS));
    }

    public static String getOTP(String email) {
        OTPData otpData = otpStorage.get(email);
        if (otpData != null && !isOTPExpired(otpData)) {
            return otpData.getOtp();
        } else {
            return null; 
        }
    }

    public static void removeOTP(String email) {
        otpStorage.remove(email);
    }

    private static boolean isOTPExpired(OTPData otpData) {
        return System.currentTimeMillis() > otpData.getExpirationTime();
    }
    
    private static void removeExpiredOTPs() {
        Iterator<Map.Entry<String, OTPData>> iterator = otpStorage.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, OTPData> entry = iterator.next();
            if (isOTPExpired(entry.getValue())) {
                iterator.remove();
            }
        }
    }

    private static class OTPData {
        private String otp;
        private long expirationTime;

        public OTPData(String otp, long expirationTime) {
            this.otp = otp;
            this.expirationTime = expirationTime;
        }

        public String getOtp() {
            return otp;
        }

        public long getExpirationTime() {
            return expirationTime;
        }
    }
}
