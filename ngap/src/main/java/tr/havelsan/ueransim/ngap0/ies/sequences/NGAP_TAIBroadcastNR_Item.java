/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_CompletedCellsInTAI_NR;

public class NGAP_TAIBroadcastNR_Item extends NGAP_Sequence {

    public NGAP_TAI tAI;
    public NGAP_CompletedCellsInTAI_NR completedCellsInTAI_NR;

    @Override
    public String getAsnName() {
        return "TAIBroadcastNR-Item";
    }

    @Override
    public String getXmlTagName() {
        return "TAIBroadcastNR-Item";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"tAI", "completedCellsInTAI-NR"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"tAI", "completedCellsInTAI_NR"};
    }
}
