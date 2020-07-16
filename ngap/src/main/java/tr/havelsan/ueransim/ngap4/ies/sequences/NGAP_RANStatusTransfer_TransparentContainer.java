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
