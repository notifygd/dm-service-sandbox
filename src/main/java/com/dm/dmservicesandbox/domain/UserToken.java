package com.dm.dmservicesandbox.domain;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "user")
public class UserToken
{
    @Id
    @Column(name = "sys_id")
    private String id;

    @Column(name = "email")
    private String email;

    @Column(name = "tmp_token")
    private String token;

    @Column(name = "tmp_token_expire_on")
    private String tokenExpireDateStr;

    @Column(name = "user_status")
    private String status;

}
