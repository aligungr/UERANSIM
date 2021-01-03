/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_CellIDBroadcastNR_Item;

import java.util.List;

public class NGAP_CellIDBroadcastNR extends NGAP_SequenceOf<NGAP_CellIDBroadcastNR_Item> {

    public NGAP_CellIDBroadcastNR() {
        super();
    }

    public NGAP_CellIDBroadcastNR(List<NGAP_CellIDBroadcastNR_Item> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "CellIDBroadcastNR";
    }

    @Override
    public String getXmlTagName() {
        return "CellIDBroadcastNR";
    }

    @Override
    public Class<NGAP_CellIDBroadcastNR_Item> getItemType() {
        return NGAP_CellIDBroadcastNR_Item.class;
    }
}
