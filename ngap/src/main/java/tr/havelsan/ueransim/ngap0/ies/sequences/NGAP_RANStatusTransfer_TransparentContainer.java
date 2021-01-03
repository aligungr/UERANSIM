/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_DRBsSubjectToStatusTransferList;

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
