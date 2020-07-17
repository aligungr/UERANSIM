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

public class NGAP_CriticalityDiagnostics extends NgapSequence {

    public NGAP_ProcedureCode procedureCode;
    public NGAP_TriggeringMessage triggeringMessage;
    public NGAP_Criticality procedureCriticality;
    public NGAP_CriticalityDiagnostics_IE_List iEsCriticalityDiagnostics;

    @Override
    public String getAsnName() {
        return "CriticalityDiagnostics";
    }

    @Override
    public String getXmlTagName() {
        return "CriticalityDiagnostics";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"procedureCode", "triggeringMessage", "procedureCriticality", "iEsCriticalityDiagnostics"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"procedureCode", "triggeringMessage", "procedureCriticality", "iEsCriticalityDiagnostics"};
    }
}
