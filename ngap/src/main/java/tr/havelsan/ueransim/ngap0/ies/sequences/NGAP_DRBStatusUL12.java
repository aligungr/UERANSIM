/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.NGAP_BitString;
import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;

public class NGAP_DRBStatusUL12 extends NGAP_Sequence {

    public NGAP_COUNTValueForPDCP_SN12 uL_COUNTValue;
    public NGAP_BitString receiveStatusOfUL_PDCP_SDUs;

    @Override
    public String getAsnName() {
        return "DRBStatusUL12";
    }

    @Override
    public String getXmlTagName() {
        return "DRBStatusUL12";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"uL-COUNTValue", "receiveStatusOfUL-PDCP-SDUs"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"uL_COUNTValue", "receiveStatusOfUL_PDCP_SDUs"};
    }
}
