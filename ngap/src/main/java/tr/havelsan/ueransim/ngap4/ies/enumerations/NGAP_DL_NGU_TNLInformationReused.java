package tr.havelsan.ueransim.ngap4.ies.enumerations;

import tr.havelsan.ueransim.ngap4.core.NgapEnumerated;

public class NGAP_DL_NGU_TNLInformationReused extends NgapEnumerated {

    public static final NGAP_DL_NGU_TNLInformationReused TRUE = new NGAP_DL_NGU_TNLInformationReused("true");

    protected NGAP_DL_NGU_TNLInformationReused(String sValue) {
        super(sValue);
    }

    @Override
    protected String getAsnName() {
        return "DL-NGU-TNLInformationReused";
    }

    @Override
    protected String getXmlTagName() {
        return "DL-NGU-TNLInformationReused";
    }
}
