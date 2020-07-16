package tr.havelsan.ueransim.ngap4.ies.enumerations;

import tr.havelsan.ueransim.ngap4.core.NgapEnumerated;

public class NGAP_TriggeringMessage extends NgapEnumerated {

    public static final NGAP_TriggeringMessage INITIATING_MESSAGE = new NGAP_TriggeringMessage("initiating-message");
    public static final NGAP_TriggeringMessage SUCCESSFUL_OUTCOME = new NGAP_TriggeringMessage("successful-outcome");
    public static final NGAP_TriggeringMessage UNSUCCESSFULL_OUTCOME = new NGAP_TriggeringMessage("unsuccessfull-outcome");

    protected NGAP_TriggeringMessage(String sValue) {
        super(sValue);
    }

    @Override
    protected String getAsnName() {
        return "TriggeringMessage";
    }

    @Override
    protected String getXmlTagName() {
        return "TriggeringMessage";
    }
}
