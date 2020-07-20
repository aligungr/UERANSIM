package tr.havelsan.ueransim.ngap0.ies.enumerations;

import tr.havelsan.ueransim.ngap0.core.*;

public class NGAP_DelayCritical extends NGAP_Enumerated {

    public static final NGAP_DelayCritical DELAY_CRITICAL = new NGAP_DelayCritical("delay-critical");
    public static final NGAP_DelayCritical NON_DELAY_CRITICAL = new NGAP_DelayCritical("non-delay-critical");

    protected NGAP_DelayCritical(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "DelayCritical";
    }

    @Override
    public String getXmlTagName() {
        return "DelayCritical";
    }
}
