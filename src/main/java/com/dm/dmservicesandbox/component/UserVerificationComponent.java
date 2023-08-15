package com.dm.dmservicesandbox.component;

import com.dm.dmservicesandbox.dbhome.UserTokenRepository;
import com.dm.dmservicesandbox.domain.EmailData;
import com.dm.dmservicesandbox.domain.UserToken;
import com.dm.dmservicesandbox.domain.ServiceReponse;

import jakarta.persistence.EntityManager;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.security.SecureRandom;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


@Component
public class UserVerificationComponent {
    @Autowired
    private EntityManager em;

    @Autowired
    MailComponent mailComponent;

    @Autowired
    private UserTokenRepository userTokenRepository;

    private static final int EMAILTOKEN_EXPIRATION = 60 * 24; // 24 hours
    private static final String REGISTER_CONF_PATH = "/dm/registerConfirmation"; // same as in UserVerificationController


    private String generateValidationToken() {
        final int BYTETOKEN_LENGTH = 8;
        SecureRandom secureRandom = new SecureRandom();
        byte[] tokenBytes = new byte[BYTETOKEN_LENGTH];
        secureRandom.nextBytes(tokenBytes);
        return new BigInteger(1, tokenBytes).toString(16);
    }
    private Date calValidationExpiryDate(int expiryTimeInMinutes) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        calendar.add(Calendar.MINUTE, expiryTimeInMinutes);
        return calendar.getTime(); // Date after expiryTimeInMinutes from now in GMT
    }

    private boolean isTokenExpired (String expireTimeStrInGMT) throws Exception {
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

    public ResponseEntity sendRegisterConfirmation(String email) {
        try {

            //calculate verification token and expire date
            String token = generateValidationToken();
            Date expireTime = calValidationExpiryDate(EMAILTOKEN_EXPIRATION);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            String expireDateStr = dateFormat.format(expireTime);

            //save verification token and expire date
            userTokenRepository.saveUserToken(email, token, expireDateStr);

            //generate verification url
            String baseURL = ServletUriComponentsBuilder.fromCurrentContextPath().toUriString();
            String validationUrl =  baseURL + REGISTER_CONF_PATH + "/" + token;

            //send email
            EmailData emailData = new EmailData();
            emailData.setReceiver(email);
            emailData.setSubject("Change Maker: Registration Confirmation");
            String message = String.format("<p>Please click below link to confirm your registration at Change Maker.</p>" +
                    "<p>%s</p><p>If the link doesn't work, you can copy and paste it to your browser.</p>", validationUrl);
            emailData.setMessage(message);
            mailComponent.sendMail(emailData);
            return ResponseEntity.ok(new ServiceReponse(true, "Registration confirmation link sent to " + email));
//            return ResponseEntity.ok(new ServiceReponse(true, "<p>For testing. Assume email is sent to " + email + " as below:</p>" + message));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ServiceReponse(false, "Sending registration confirmation link Error: " + e.getMessage()));
        }
    }

    public ResponseEntity<?> verifyUserToken(String token) {
        try {
            UserToken userToken = userTokenRepository.findUserByToken(token);
            if (userToken == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ServiceReponse(false, "Registration confirmation invalid."));
            }
            else if( isTokenExpired(userToken.getTokenExpireDateStr()) == true ) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ServiceReponse(false, "Registration confirmation expired."));
            }else{
                userTokenRepository.saveUserVerified(userToken.getEmail());
                return ResponseEntity.ok(new ServiceReponse(true, "Registration confirmed successfully."));
            }
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(new ServiceReponse(false,"Error Registration confirmation: " + e.getMessage()));
        }
    }


}
