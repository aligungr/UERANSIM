/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_XnExtTLA_Item;

import java.util.List;

public class NGAP_XnExtTLAs extends NGAP_SequenceOf<NGAP_XnExtTLA_Item> {

    public NGAP_XnExtTLAs() {
        super();
    }

    public NGAP_XnExtTLAs(List<NGAP_XnExtTLA_Item> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "XnExtTLAs";
    }

    @Override
    public String getXmlTagName() {
        return "XnExtTLAs";
    }

    @Override
    public Class<NGAP_XnExtTLA_Item> getItemType() {
        return NGAP_XnExtTLA_Item.class;
    }
}
