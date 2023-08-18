package com.dm.dmservicesandbox.domain.dbojects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User implements Serializable  {

    @Serial
    private static final long serialVersionUID = 65981149772133526L;

    @Column(name = "email")
    private String email;

    @Column(name = "user_status")
    private String userStatus;

    @Id
    @Column(name = "user_name")
    private String userName;

    @Column(name = "location_id")
    private String userLocation;

    @Column(name = "created_on", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createdDate;

    @Column(name = "updated_on", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date updatedDate;

    @Column(name = "pwd")
    private String password;

    @Column(name = "description")
    private String description;

    @Column(name = "is_current")
    private int isCurrent;

    // bi-directional many-to-many association to Role
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
    private Set<Role> roles;
}