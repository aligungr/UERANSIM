package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.enumerations.NGAP_Criticality;
import tr.havelsan.ueransim.ngap4.ies.enumerations.NGAP_TriggeringMessage;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_ProcedureCode;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.NGAP_CriticalityDiagnostics_IE_List;

public class NGAP_CriticalityDiagnostics extends NgapSequence {

    public NGAP_ProcedureCode procedureCode;
    public NGAP_TriggeringMessage triggeringMessage;
    public NGAP_Criticality procedureCriticality;
    public NGAP_CriticalityDiagnostics_IE_List iEsCriticalityDiagnostics;

    @Override
    protected String getAsnName() {
        return "CriticalityDiagnostics";
    }

    @Override
    protected String getXmlTagName() {
        return "CriticalityDiagnostics";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"procedureCode", "triggeringMessage", "procedureCriticality", "iEsCriticalityDiagnostics"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"procedureCode", "triggeringMessage", "procedureCriticality", "iEsCriticalityDiagnostics"};
    }
}
