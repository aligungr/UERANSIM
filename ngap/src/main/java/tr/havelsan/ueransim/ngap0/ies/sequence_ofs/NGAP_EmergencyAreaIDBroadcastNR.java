/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_EmergencyAreaIDBroadcastNR_Item;

import java.util.List;

public class NGAP_EmergencyAreaIDBroadcastNR extends NGAP_SequenceOf<NGAP_EmergencyAreaIDBroadcastNR_Item> {

    public NGAP_EmergencyAreaIDBroadcastNR() {
        super();
    }

    public NGAP_EmergencyAreaIDBroadcastNR(List<NGAP_EmergencyAreaIDBroadcastNR_Item> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "EmergencyAreaIDBroadcastNR";
    }

    @Override
    public String getXmlTagName() {
        return "EmergencyAreaIDBroadcastNR";
    }

    @Override
    public Class<NGAP_EmergencyAreaIDBroadcastNR_Item> getItemType() {
        return NGAP_EmergencyAreaIDBroadcastNR_Item.class;
    }
}
