/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_XnExtTLAs;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_XnTLAs;

public class NGAP_XnTNLConfigurationInfo extends NGAP_Sequence {

    public NGAP_XnTLAs xnTransportLayerAddresses;
    public NGAP_XnExtTLAs xnExtendedTransportLayerAddresses;

    @Override
    public String getAsnName() {
        return "XnTNLConfigurationInfo";
    }

    @Override
    public String getXmlTagName() {
        return "XnTNLConfigurationInfo";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"xnTransportLayerAddresses", "xnExtendedTransportLayerAddresses"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"xnTransportLayerAddresses", "xnExtendedTransportLayerAddresses"};
    }
}
