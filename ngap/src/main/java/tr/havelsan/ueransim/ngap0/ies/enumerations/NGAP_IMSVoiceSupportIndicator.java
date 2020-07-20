package tr.havelsan.ueransim.ngap0.ies.enumerations;

import tr.havelsan.ueransim.ngap0.core.*;

public class NGAP_IMSVoiceSupportIndicator extends NGAP_Enumerated {

    public static final NGAP_IMSVoiceSupportIndicator SUPPORTED = new NGAP_IMSVoiceSupportIndicator("supported");
    public static final NGAP_IMSVoiceSupportIndicator NOT_SUPPORTED = new NGAP_IMSVoiceSupportIndicator("not-supported");

    protected NGAP_IMSVoiceSupportIndicator(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "IMSVoiceSupportIndicator";
    }

    @Override
    public String getXmlTagName() {
        return "IMSVoiceSupportIndicator";
    }
}
