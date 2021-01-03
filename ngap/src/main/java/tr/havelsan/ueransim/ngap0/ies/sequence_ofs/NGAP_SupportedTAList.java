/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_SupportedTAItem;

import java.util.List;

public class NGAP_SupportedTAList extends NGAP_SequenceOf<NGAP_SupportedTAItem> {

    public NGAP_SupportedTAList() {
        super();
    }

    public NGAP_SupportedTAList(List<NGAP_SupportedTAItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "SupportedTAList";
    }

    @Override
    public String getXmlTagName() {
        return "SupportedTAList";
    }

    @Override
    public Class<NGAP_SupportedTAItem> getItemType() {
        return NGAP_SupportedTAItem.class;
    }
}
