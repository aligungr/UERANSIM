package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.*;

public class NGAP_RANStatusTransfer_TransparentContainer extends NGAP_Sequence {

    public NGAP_DRBsSubjectToStatusTransferList dRBsSubjectToStatusTransferList;

    @Override
    public String getAsnName() {
        return "RANStatusTransfer-TransparentContainer";
    }

    @Override
    public String getXmlTagName() {
        return "RANStatusTransfer-TransparentContainer";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"dRBsSubjectToStatusTransferList"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"dRBsSubjectToStatusTransferList"};
    }
}
