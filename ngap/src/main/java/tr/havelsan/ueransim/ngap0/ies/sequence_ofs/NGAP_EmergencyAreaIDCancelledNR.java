/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_EmergencyAreaIDCancelledNR_Item;

import java.util.List;

public class NGAP_EmergencyAreaIDCancelledNR extends NGAP_SequenceOf<NGAP_EmergencyAreaIDCancelledNR_Item> {

    public NGAP_EmergencyAreaIDCancelledNR() {
        super();
    }

    public NGAP_EmergencyAreaIDCancelledNR(List<NGAP_EmergencyAreaIDCancelledNR_Item> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "EmergencyAreaIDCancelledNR";
    }

    @Override
    public String getXmlTagName() {
        return "EmergencyAreaIDCancelledNR";
    }

    @Override
    public Class<NGAP_EmergencyAreaIDCancelledNR_Item> getItemType() {
        return NGAP_EmergencyAreaIDCancelledNR_Item.class;
    }
}
