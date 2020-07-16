package tr.havelsan.ueransim.ngap4.ies.enumerations;

import tr.havelsan.ueransim.ngap4.core.NgapEnumerated;

public class NGAP_NotificationControl extends NgapEnumerated {

    public static final NGAP_NotificationControl NOTIFICATION_REQUESTED = new NGAP_NotificationControl("notification-requested");

    protected NGAP_NotificationControl(String sValue) {
        super(sValue);
    }

    @Override
    protected String getAsnName() {
        return "NotificationControl";
    }

    @Override
    protected String getXmlTagName() {
        return "NotificationControl";
    }
}
