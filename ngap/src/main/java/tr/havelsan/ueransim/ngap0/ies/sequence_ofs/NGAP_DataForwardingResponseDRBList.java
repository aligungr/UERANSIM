/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_DataForwardingResponseDRBItem;

import java.util.List;

public class NGAP_DataForwardingResponseDRBList extends NGAP_SequenceOf<NGAP_DataForwardingResponseDRBItem> {

    public NGAP_DataForwardingResponseDRBList() {
        super();
    }

    public NGAP_DataForwardingResponseDRBList(List<NGAP_DataForwardingResponseDRBItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "DataForwardingResponseDRBList";
    }

    @Override
    public String getXmlTagName() {
        return "DataForwardingResponseDRBList";
    }

    @Override
    public Class<NGAP_DataForwardingResponseDRBItem> getItemType() {
        return NGAP_DataForwardingResponseDRBItem.class;
    }
}
