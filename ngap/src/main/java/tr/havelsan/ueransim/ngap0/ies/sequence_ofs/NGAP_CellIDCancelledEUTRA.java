/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_CellIDCancelledEUTRA_Item;

import java.util.List;

public class NGAP_CellIDCancelledEUTRA extends NGAP_SequenceOf<NGAP_CellIDCancelledEUTRA_Item> {

    public NGAP_CellIDCancelledEUTRA() {
        super();
    }

    public NGAP_CellIDCancelledEUTRA(List<NGAP_CellIDCancelledEUTRA_Item> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "CellIDCancelledEUTRA";
    }

    @Override
    public String getXmlTagName() {
        return "CellIDCancelledEUTRA";
    }

    @Override
    public Class<NGAP_CellIDCancelledEUTRA_Item> getItemType() {
        return NGAP_CellIDCancelledEUTRA_Item.class;
    }
}
