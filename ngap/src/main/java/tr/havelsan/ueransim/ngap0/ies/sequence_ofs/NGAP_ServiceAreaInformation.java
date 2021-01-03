/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_ServiceAreaInformation_Item;

import java.util.List;

public class NGAP_ServiceAreaInformation extends NGAP_SequenceOf<NGAP_ServiceAreaInformation_Item> {

    public NGAP_ServiceAreaInformation() {
        super();
    }

    public NGAP_ServiceAreaInformation(List<NGAP_ServiceAreaInformation_Item> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "ServiceAreaInformation";
    }

    @Override
    public String getXmlTagName() {
        return "ServiceAreaInformation";
    }

    @Override
    public Class<NGAP_ServiceAreaInformation_Item> getItemType() {
        return NGAP_ServiceAreaInformation_Item.class;
    }
}
