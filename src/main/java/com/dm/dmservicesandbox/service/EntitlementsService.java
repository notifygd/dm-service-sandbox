package com.dm.dmservicesandbox.service;

import com.dm.dmservicesandbox.ui.UIOperation;
import org.springframework.stereotype.Service;

@Service
public class EntitlementsService {
    public boolean isEntitled(String userName, UIOperation uiOperation){
        //TODO logic to check entitlement
        return true;
    }
}