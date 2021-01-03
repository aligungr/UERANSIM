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
import tr.havelsan.ueransim.ngap0.ies.octet_strings.NGAP_PortNumber;

public class NGAP_EndpointIPAddressAndPort extends NGAP_Sequence {

    public NGAP_TransportLayerAddress endpointIPAddress;
    public NGAP_PortNumber portNumber;

    @Override
    public String getAsnName() {
        return "EndpointIPAddressAndPort";
    }

    @Override
    public String getXmlTagName() {
        return "EndpointIPAddressAndPort";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"endpointIPAddress", "portNumber"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"endpointIPAddress", "portNumber"};
    }
}
