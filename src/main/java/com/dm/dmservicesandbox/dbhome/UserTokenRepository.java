package com.dm.dmservicesandbox.dbhome;

import com.dm.dmservicesandbox.domain.UserToken;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserTokenRepository {

    @PersistenceContext
    private EntityManager em;

    public UserToken findUserByToken(String token) {
        TypedQuery<UserToken> query = em.createQuery( "SELECT u FROM UserToken u where u.token = :value", UserToken.class);
        query.setParameter("value", token);
        List<UserToken> resultList = query.getResultList();
        if(resultList.size() > 0) {
            return resultList.get(0);
        } else {
            return null;
        }
    }

    @Transactional
    public int saveUserToken(String email, String token, String expireDateStr)  {
        Query query = em.createQuery("UPDATE UserToken SET token=:token, tokenExpireDateStr=:expire_date, status='verifying' WHERE email=:email");
        return query.setParameter("token", token)
                                .setParameter("expire_date", expireDateStr)
                                .setParameter("email", email)
                                .executeUpdate();
    }

    @Transactional
    public int saveUserVerified(String email)  {
        Query query = em.createQuery("UPDATE UserToken SET token='', tokenExpireDateStr=null, status='verified' WHERE email=:email");
        return query.setParameter("email", email).executeUpdate();
    }

}
