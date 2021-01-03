/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_RATRestrictions_Item;

import java.util.List;

public class NGAP_RATRestrictions extends NGAP_SequenceOf<NGAP_RATRestrictions_Item> {

    public NGAP_RATRestrictions() {
        super();
    }

    public NGAP_RATRestrictions(List<NGAP_RATRestrictions_Item> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "RATRestrictions";
    }

    @Override
    public String getXmlTagName() {
        return "RATRestrictions";
    }

    @Override
    public Class<NGAP_RATRestrictions_Item> getItemType() {
        return NGAP_RATRestrictions_Item.class;
    }
}
