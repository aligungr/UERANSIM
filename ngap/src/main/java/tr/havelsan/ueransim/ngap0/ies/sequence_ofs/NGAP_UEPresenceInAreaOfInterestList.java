/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_UEPresenceInAreaOfInterestItem;

import java.util.List;

public class NGAP_UEPresenceInAreaOfInterestList extends NGAP_SequenceOf<NGAP_UEPresenceInAreaOfInterestItem> {

    public NGAP_UEPresenceInAreaOfInterestList() {
        super();
    }

    public NGAP_UEPresenceInAreaOfInterestList(List<NGAP_UEPresenceInAreaOfInterestItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "UEPresenceInAreaOfInterestList";
    }

    @Override
    public String getXmlTagName() {
        return "UEPresenceInAreaOfInterestList";
    }

    @Override
    public Class<NGAP_UEPresenceInAreaOfInterestItem> getItemType() {
        return NGAP_UEPresenceInAreaOfInterestItem.class;
    }
}
