/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_CancelledCellsInTAI_NR;

public class NGAP_TAICancelledNR_Item extends NGAP_Sequence {

    public NGAP_TAI tAI;
    public NGAP_CancelledCellsInTAI_NR cancelledCellsInTAI_NR;

    @Override
    public String getAsnName() {
        return "TAICancelledNR-Item";
    }

    @Override
    public String getXmlTagName() {
        return "TAICancelledNR-Item";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"tAI", "cancelledCellsInTAI-NR"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"tAI", "cancelledCellsInTAI_NR"};
    }
}
