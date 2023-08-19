package com.dm.dmservicesandbox.component;

import com.dm.dmservicesandbox.dbhome.UserTokenRepository;
import com.dm.dmservicesandbox.domain.EmailData;
import com.dm.dmservicesandbox.domain.UserToken;
import com.dm.dmservicesandbox.domain.APINames;

import com.dm.dmservicesandbox.domain.restservice.ServiceReponse;
import jakarta.persistence.EntityManager;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


@Component
public class UserVerificationComponent {
    @Autowired
    private EntityManager em;


    @Autowired
    private UserTokenRepository userTokenRepository;

    @Autowired
    MailClient mailClient;

    @Autowired
    UserTokenComponent userTokenComponent;



    public ResponseEntity sendRegisterConfirmation(String email) {
        try {

            //calculate verification token and expire date
            //calculate verification token and expire date
            String token = userTokenComponent.generateUserToken();
            Date expireTime = userTokenComponent.calTokenExpiryDate();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            String expireDateStr = dateFormat.format(expireTime);

            //save verification token and expire date to DB
            userTokenRepository.saveUserToken(email, token, expireDateStr);

            //save verification token and expire date
            userTokenRepository.saveUserToken(email, token, expireDateStr);

            //generate verification url
            String baseURL = ServletUriComponentsBuilder.fromCurrentContextPath().toUriString();
            String validationUrl =  baseURL + APINames.REGISTER_CONF_API + "/" + token;

            //send email
            EmailData emailData = new EmailData();
            emailData.setReceiver(email);
            emailData.setSubject("Change Maker: Registration Confirmation");
            String message = String.format("<p>Please click below link to confirm your registration at Change Maker.</p>" +
                    "<p>%s</p><p>If the link doesn't work, you can copy and paste it to your browser.</p>", validationUrl);
            emailData.setMessage(message);
            mailClient.sendMail(emailData);
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
            else if( userTokenComponent.isTokenExpired(userToken.getTokenExpireDateStr()) == true ) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ServiceReponse(false, "Registration confirmation expired."));
            }else{
                userTokenRepository.saveUserTokenVerified(userToken.getEmail());
                //TODO change user.user_status to "verified"
                return ResponseEntity.ok(new ServiceReponse(true, "Registration confirmed successfully."));
            }
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(new ServiceReponse(false,"Error Registration confirmation: " + e.getMessage()));
        }
    }


}
