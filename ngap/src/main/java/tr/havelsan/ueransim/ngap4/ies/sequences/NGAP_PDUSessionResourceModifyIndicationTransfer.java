package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.choices.NGAP_UP_TNLInformation;

public class NGAP_PDUSessionResourceModifyIndicationTransfer extends NgapSequence {

    public NGAP_UP_TNLInformation dL_UP_TNLInformation;

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceModifyIndicationTransfer";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceModifyIndicationTransfer";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"dL-UP-TNLInformation"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"dL_UP_TNLInformation"};
    }
}
