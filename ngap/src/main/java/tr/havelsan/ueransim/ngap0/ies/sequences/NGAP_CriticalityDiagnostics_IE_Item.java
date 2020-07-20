package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;
import tr.havelsan.ueransim.ngap0.ies.enumerations.*;

public class NGAP_CriticalityDiagnostics_IE_Item extends NGAP_Sequence {

    public NGAP_Criticality iECriticality;
    public NGAP_ProtocolIE_ID iE_ID;
    public NGAP_TypeOfError typeOfError;

    @Override
    public String getAsnName() {
        return "CriticalityDiagnostics-IE-Item";
    }

    @Override
    public String getXmlTagName() {
        return "CriticalityDiagnostics-IE-Item";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"iECriticality", "iE-ID", "typeOfError"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"iECriticality", "iE_ID", "typeOfError"};
    }
}
