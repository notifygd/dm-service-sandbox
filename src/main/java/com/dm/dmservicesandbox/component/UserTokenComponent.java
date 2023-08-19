package com.dm.dmservicesandbox.component;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@Component
public class UserTokenComponent {

    private static final int EMAILTOKEN_EXPIRATION = 60 * 24; // 24 hours


    public String generateUserToken() {
        final int BYTETOKEN_LENGTH = 8;
        SecureRandom secureRandom = new SecureRandom();
        byte[] tokenBytes = new byte[BYTETOKEN_LENGTH];
        secureRandom.nextBytes(tokenBytes);
        return new BigInteger(1, tokenBytes).toString(16);
    }

    public Date calTokenExpiryDate() {
        int expiryTimeInMinutes = EMAILTOKEN_EXPIRATION;
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        calendar.add(Calendar.MINUTE, expiryTimeInMinutes);
        return calendar.getTime(); // Date after expiryTimeInMinutes from now in GMT
    }

    public boolean isTokenExpired (String expireTimeStrInGMT) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date parsedExpireTime = null;
        try {
            parsedExpireTime = dateFormat.parse(expireTimeStrInGMT);
        } catch (Exception e) {
            throw e;
        }
        if(parsedExpireTime == null) {
            throw new Exception("Invalid expire date: " + expireTimeStrInGMT);
        }
        // Get the current time in GMT
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        Date currentDateTime = calendar.getTime();
        // Compare the parsed date with the current time
        if (parsedExpireTime != null && parsedExpireTime.before(currentDateTime)) {
            return true;
        } else {
            return false;
        }
    }

}
