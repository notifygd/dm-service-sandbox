package com.dm.dmservicesandbox.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLocation {
    private String countryOfResidence;
    private String city;
}
