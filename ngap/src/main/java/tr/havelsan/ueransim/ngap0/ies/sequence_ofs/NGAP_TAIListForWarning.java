/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_TAI;

import java.util.List;

public class NGAP_TAIListForWarning extends NGAP_SequenceOf<NGAP_TAI> {

    public NGAP_TAIListForWarning() {
        super();
    }

    public NGAP_TAIListForWarning(List<NGAP_TAI> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "TAIListForWarning";
    }

    @Override
    public String getXmlTagName() {
        return "TAIListForWarning";
    }

    @Override
    public Class<NGAP_TAI> getItemType() {
        return NGAP_TAI.class;
    }
}
