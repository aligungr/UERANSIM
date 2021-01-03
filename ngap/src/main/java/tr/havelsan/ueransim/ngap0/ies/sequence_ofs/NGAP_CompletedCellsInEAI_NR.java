/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_CompletedCellsInEAI_NR_Item;

import java.util.List;

public class NGAP_CompletedCellsInEAI_NR extends NGAP_SequenceOf<NGAP_CompletedCellsInEAI_NR_Item> {

    public NGAP_CompletedCellsInEAI_NR() {
        super();
    }

    public NGAP_CompletedCellsInEAI_NR(List<NGAP_CompletedCellsInEAI_NR_Item> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "CompletedCellsInEAI-NR";
    }

    @Override
    public String getXmlTagName() {
        return "CompletedCellsInEAI-NR";
    }

    @Override
    public Class<NGAP_CompletedCellsInEAI_NR_Item> getItemType() {
        return NGAP_CompletedCellsInEAI_NR_Item.class;
    }
}
