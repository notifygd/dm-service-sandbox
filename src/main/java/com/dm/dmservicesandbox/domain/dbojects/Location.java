package com.dm.dmservicesandbox.domain.dbojects;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "location")
public class Location implements Serializable {

    @Serial
    private static final long serialVersionUID = 65981149772133526L;

    @Id
    @Column(name = "sys_id")
    private String SystemId;

    @Column(name = "country")
    private String country;


    @Column(name = "region")
    private String region;

    @Column(name = "district")
    private String district;

    @Column(name = "city")
    private String city;

    @Column(name = "longitute")
    private Float longitute;

    @Column(name = "latitude")
    private Float latitude;

    @Column(name = "created_on", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createdDate;

    @Column(name = "updated_on", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date updatedDate;

}
