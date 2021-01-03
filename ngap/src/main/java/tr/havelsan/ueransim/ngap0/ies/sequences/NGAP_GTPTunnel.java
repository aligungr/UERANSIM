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
import tr.havelsan.ueransim.ngap0.ies.octet_strings.NGAP_GTP_TEID;

public class NGAP_GTPTunnel extends NGAP_Sequence {

    public NGAP_TransportLayerAddress transportLayerAddress;
    public NGAP_GTP_TEID gTP_TEID;

    @Override
    public String getAsnName() {
        return "GTPTunnel";
    }

    @Override
    public String getXmlTagName() {
        return "GTPTunnel";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"transportLayerAddress", "gTP-TEID"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"transportLayerAddress", "gTP_TEID"};
    }
}
