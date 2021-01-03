/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_UPTransportLayerInformationItem;

import java.util.List;

public class NGAP_UPTransportLayerInformationList extends NGAP_SequenceOf<NGAP_UPTransportLayerInformationItem> {

    public NGAP_UPTransportLayerInformationList() {
        super();
    }

    public NGAP_UPTransportLayerInformationList(List<NGAP_UPTransportLayerInformationItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "UPTransportLayerInformationList";
    }

    @Override
    public String getXmlTagName() {
        return "UPTransportLayerInformationList";
    }

    @Override
    public Class<NGAP_UPTransportLayerInformationItem> getItemType() {
        return NGAP_UPTransportLayerInformationItem.class;
    }
}
