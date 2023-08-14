package com.dm.dmservicesandbox.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper=true, includeFieldNames=true)
@Table(name = "user_location")
public class UserLocation implements Serializable {

    @Serial
    private static final long serialVersionUID = 65981149772133526L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Long locationId;

    @Column(name = "map_box_id")
    private String mapBoxId;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

}
