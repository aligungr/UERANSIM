package tr.havelsan.ueransim.ngap4.ies.enumerations;

import tr.havelsan.ueransim.ngap4.core.NgapEnumerated;

public class NGAP_IMSVoiceSupportIndicator extends NgapEnumerated {

    public static final NGAP_IMSVoiceSupportIndicator SUPPORTED = new NGAP_IMSVoiceSupportIndicator("supported");
    public static final NGAP_IMSVoiceSupportIndicator NOT_SUPPORTED = new NGAP_IMSVoiceSupportIndicator("not-supported");

    protected NGAP_IMSVoiceSupportIndicator(String sValue) {
        super(sValue);
    }

    @Override
    protected String getAsnName() {
        return "IMSVoiceSupportIndicator";
    }

    @Override
    protected String getXmlTagName() {
        return "IMSVoiceSupportIndicator";
    }
}
