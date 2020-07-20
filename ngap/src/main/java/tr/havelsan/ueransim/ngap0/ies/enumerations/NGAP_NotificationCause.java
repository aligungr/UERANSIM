package tr.havelsan.ueransim.ngap0.ies.enumerations;

import tr.havelsan.ueransim.ngap0.core.*;

public class NGAP_NotificationCause extends NGAP_Enumerated {

    public static final NGAP_NotificationCause FULFILLED = new NGAP_NotificationCause("fulfilled");
    public static final NGAP_NotificationCause NOT_FULFILLED = new NGAP_NotificationCause("not-fulfilled");

    protected NGAP_NotificationCause(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "NotificationCause";
    }

    @Override
    public String getXmlTagName() {
        return "NotificationCause";
    }
}
