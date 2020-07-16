package tr.havelsan.ueransim.ngap4.ies.enumerations;

import tr.havelsan.ueransim.ngap4.core.NgapEnumerated;

public class NGAP_MaximumIntegrityProtectedDataRate extends NgapEnumerated {

    public static final NGAP_MaximumIntegrityProtectedDataRate BITRATE64KBS = new NGAP_MaximumIntegrityProtectedDataRate("bitrate64kbs");
    public static final NGAP_MaximumIntegrityProtectedDataRate MAXIMUM_UE_RATE = new NGAP_MaximumIntegrityProtectedDataRate("maximum-UE-rate");

    protected NGAP_MaximumIntegrityProtectedDataRate(String sValue) {
        super(sValue);
    }

    @Override
    protected String getAsnName() {
        return "MaximumIntegrityProtectedDataRate";
    }

    @Override
    protected String getXmlTagName() {
        return "MaximumIntegrityProtectedDataRate";
    }
}
