/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.bit_strings.NGAP_TransportLayerAddress;

import java.util.List;

public class NGAP_XnGTP_TLAs extends NGAP_SequenceOf<NGAP_TransportLayerAddress> {

    public NGAP_XnGTP_TLAs() {
        super();
    }

    public NGAP_XnGTP_TLAs(List<NGAP_TransportLayerAddress> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "XnGTP-TLAs";
    }

    @Override
    public String getXmlTagName() {
        return "XnGTP-TLAs";
    }

    @Override
    public Class<NGAP_TransportLayerAddress> getItemType() {
        return NGAP_TransportLayerAddress.class;
    }
}
