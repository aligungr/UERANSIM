/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_UPTransportLayerInformationPairItem;

import java.util.List;

public class NGAP_UPTransportLayerInformationPairList extends NGAP_SequenceOf<NGAP_UPTransportLayerInformationPairItem> {

    public NGAP_UPTransportLayerInformationPairList() {
        super();
    }

    public NGAP_UPTransportLayerInformationPairList(List<NGAP_UPTransportLayerInformationPairItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "UPTransportLayerInformationPairList";
    }

    @Override
    public String getXmlTagName() {
        return "UPTransportLayerInformationPairList";
    }

    @Override
    public Class<NGAP_UPTransportLayerInformationPairItem> getItemType() {
        return NGAP_UPTransportLayerInformationPairItem.class;
    }
}
