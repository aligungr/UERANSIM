package tr.havelsan.ueransim.ngap0.ies.enumerations;

import tr.havelsan.ueransim.ngap0.core.*;

public class NGAP_MaximumIntegrityProtectedDataRate extends NGAP_Enumerated {

    public static final NGAP_MaximumIntegrityProtectedDataRate BITRATE64KBS = new NGAP_MaximumIntegrityProtectedDataRate("bitrate64kbs");
    public static final NGAP_MaximumIntegrityProtectedDataRate MAXIMUM_UE_RATE = new NGAP_MaximumIntegrityProtectedDataRate("maximum-UE-rate");

    protected NGAP_MaximumIntegrityProtectedDataRate(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "MaximumIntegrityProtectedDataRate";
    }

    @Override
    public String getXmlTagName() {
        return "MaximumIntegrityProtectedDataRate";
    }
}
