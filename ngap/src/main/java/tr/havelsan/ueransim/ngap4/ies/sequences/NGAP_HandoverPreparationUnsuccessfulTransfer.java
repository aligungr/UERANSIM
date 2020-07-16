package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.choices.NGAP_Cause;

public class NGAP_HandoverPreparationUnsuccessfulTransfer extends NgapSequence {

    public NGAP_Cause cause;

    @Override
    protected String getAsnName() {
        return "HandoverPreparationUnsuccessfulTransfer";
    }

    @Override
    protected String getXmlTagName() {
        return "HandoverPreparationUnsuccessfulTransfer";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"cause"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"cause"};
    }
}
