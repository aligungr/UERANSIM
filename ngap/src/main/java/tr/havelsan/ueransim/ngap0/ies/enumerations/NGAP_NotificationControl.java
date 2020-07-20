package tr.havelsan.ueransim.ngap0.ies.enumerations;

import tr.havelsan.ueransim.ngap0.core.*;

public class NGAP_NotificationControl extends NGAP_Enumerated {

    public static final NGAP_NotificationControl NOTIFICATION_REQUESTED = new NGAP_NotificationControl("notification-requested");

    protected NGAP_NotificationControl(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "NotificationControl";
    }

    @Override
    public String getXmlTagName() {
        return "NotificationControl";
    }
}
