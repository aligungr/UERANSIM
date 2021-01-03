/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;
import tr.havelsan.ueransim.ngap0.ies.bit_strings.NGAP_TransportLayerAddress;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_XnGTP_TLAs;

public class NGAP_XnExtTLA_Item extends NGAP_Sequence {

    public NGAP_TransportLayerAddress iPsecTLA;
    public NGAP_XnGTP_TLAs gTP_TLAs;

    @Override
    public String getAsnName() {
        return "XnExtTLA-Item";
    }

    @Override
    public String getXmlTagName() {
        return "XnExtTLA-Item";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"iPsecTLA", "gTP-TLAs"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"iPsecTLA", "gTP_TLAs"};
    }
}
