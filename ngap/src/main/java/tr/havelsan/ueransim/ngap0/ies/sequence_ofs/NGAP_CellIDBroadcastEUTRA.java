/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_CellIDBroadcastEUTRA_Item;

import java.util.List;

public class NGAP_CellIDBroadcastEUTRA extends NGAP_SequenceOf<NGAP_CellIDBroadcastEUTRA_Item> {

    public NGAP_CellIDBroadcastEUTRA() {
        super();
    }

    public NGAP_CellIDBroadcastEUTRA(List<NGAP_CellIDBroadcastEUTRA_Item> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "CellIDBroadcastEUTRA";
    }

    @Override
    public String getXmlTagName() {
        return "CellIDBroadcastEUTRA";
    }

    @Override
    public Class<NGAP_CellIDBroadcastEUTRA_Item> getItemType() {
        return NGAP_CellIDBroadcastEUTRA_Item.class;
    }
}
