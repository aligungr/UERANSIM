/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_DRB_ID;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_AssociatedQosFlowList;

public class NGAP_DRBsToQosFlowsMappingItem extends NGAP_Sequence {

    public NGAP_DRB_ID dRB_ID;
    public NGAP_AssociatedQosFlowList associatedQosFlowList;

    @Override
    public String getAsnName() {
        return "DRBsToQosFlowsMappingItem";
    }

    @Override
    public String getXmlTagName() {
        return "DRBsToQosFlowsMappingItem";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"dRB-ID", "associatedQosFlowList"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"dRB_ID", "associatedQosFlowList"};
    }
}
