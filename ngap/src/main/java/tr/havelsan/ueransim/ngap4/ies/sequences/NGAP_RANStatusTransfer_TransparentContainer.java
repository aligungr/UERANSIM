package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.NGAP_DRBsSubjectToStatusTransferList;

public class NGAP_RANStatusTransfer_TransparentContainer extends NgapSequence {

    public NGAP_DRBsSubjectToStatusTransferList dRBsSubjectToStatusTransferList;

    @Override
    protected String getAsnName() {
        return "RANStatusTransfer-TransparentContainer";
    }

    @Override
    protected String getXmlTagName() {
        return "RANStatusTransfer-TransparentContainer";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"dRBsSubjectToStatusTransferList"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"dRBsSubjectToStatusTransferList"};
    }
}
