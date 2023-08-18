package com.dm.dmservicesandbox.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmailData {

    private String receiver;
    private String subject;
    private String message;

}
