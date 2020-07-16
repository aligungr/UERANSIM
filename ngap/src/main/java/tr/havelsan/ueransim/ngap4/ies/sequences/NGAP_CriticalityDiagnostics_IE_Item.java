package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.enumerations.NGAP_Criticality;
import tr.havelsan.ueransim.ngap4.ies.enumerations.NGAP_TypeOfError;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_ProtocolIE_ID;

public class NGAP_CriticalityDiagnostics_IE_Item extends NgapSequence {

    public NGAP_Criticality iECriticality;
    public NGAP_ProtocolIE_ID iE_ID;
    public NGAP_TypeOfError typeOfError;

    @Override
    protected String getAsnName() {
        return "CriticalityDiagnostics-IE-Item";
    }

    @Override
    protected String getXmlTagName() {
        return "CriticalityDiagnostics-IE-Item";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"iECriticality", "iE-ID", "typeOfError"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"iECriticality", "iE_ID", "typeOfError"};
    }
}
