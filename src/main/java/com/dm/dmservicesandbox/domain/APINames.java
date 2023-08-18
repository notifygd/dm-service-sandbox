package com.dm.dmservicesandbox.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class APINames {

    /// dm/inviteUser/{email}
    public final static String INVITE_USER_API = "/dm/inviteUser";

    // /dm/acceptInvite/{token}
    public final static String ACCEPT_INVITE_API = "/dm/acceptInvite";

    // /dm/registerConfirmation/{token}
    public final static String REGISTER_CONF_API = "/dm/registerConfirmation";

    // /dm/redoVerification/{email}
    public final static String REDO_VERIFY_API = "/dm/redoVerification";

}
