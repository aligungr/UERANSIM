/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.choices;

import tr.havelsan.ueransim.ngap0.core.NGAP_Choice;
import tr.havelsan.ueransim.ngap0.ies.bit_strings.NGAP_TransportLayerAddress;

public class NGAP_CPTransportLayerInformation extends NGAP_Choice {

    public NGAP_TransportLayerAddress endpointIPAddress;

    @Override
    public String getAsnName() {
        return "CPTransportLayerInformation";
    }

    @Override
    public String getXmlTagName() {
        return "CPTransportLayerInformation";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"endpointIPAddress"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"endpointIPAddress"};
    }
}
