package com.dm.dmservicesandbox.domain;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "user_token")
public class UserToken
{
    @Id
    @Column(name = "email")
    private String email;

    @Column(name = "token")
    private String token;

    @Column(name = "token_expire_on")
    private String tokenExpireDateStr;


}
