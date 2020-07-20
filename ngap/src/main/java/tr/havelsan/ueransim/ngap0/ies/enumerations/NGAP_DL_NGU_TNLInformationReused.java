package tr.havelsan.ueransim.ngap0.ies.enumerations;

import tr.havelsan.ueransim.ngap0.core.*;

public class NGAP_DL_NGU_TNLInformationReused extends NGAP_Enumerated {

    public static final NGAP_DL_NGU_TNLInformationReused TRUE = new NGAP_DL_NGU_TNLInformationReused("true");

    protected NGAP_DL_NGU_TNLInformationReused(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "DL-NGU-TNLInformationReused";
    }

    @Override
    public String getXmlTagName() {
        return "DL-NGU-TNLInformationReused";
    }
}
