package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.choices.NGAP_Cause;

public class NGAP_PDUSessionResourceModifyUnsuccessfulTransfer extends NgapSequence {

    public NGAP_Cause cause;
    public NGAP_CriticalityDiagnostics criticalityDiagnostics;

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceModifyUnsuccessfulTransfer";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceModifyUnsuccessfulTransfer";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"cause", "criticalityDiagnostics"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"cause", "criticalityDiagnostics"};
    }
}
