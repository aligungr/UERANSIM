package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.*;
import tr.havelsan.ueransim.utils.bits.*;
import tr.havelsan.ueransim.utils.octets.*;
import tr.havelsan.ueransim.ngap4.ies.bit_strings.*;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.*;
import tr.havelsan.ueransim.ngap4.ies.printable_strings.*;
import tr.havelsan.ueransim.ngap4.ies.sequences.*;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.*;
import tr.havelsan.ueransim.ngap4.ies.choices.*;
import tr.havelsan.ueransim.ngap4.ies.integers.*;
import tr.havelsan.ueransim.ngap4.ies.enumerations.*;

import java.util.List;

public class NGAP_CriticalityDiagnostics_IE_Item extends NgapSequence {

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
