/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.choices;

import tr.havelsan.ueransim.ngap0.core.NGAP_Choice;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_GTPTunnel;

public class NGAP_UPTransportLayerInformation extends NGAP_Choice {

    public NGAP_GTPTunnel gTPTunnel;

    @Override
    public String getAsnName() {
        return "UPTransportLayerInformation";
    }

    @Override
    public String getXmlTagName() {
        return "UPTransportLayerInformation";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"gTPTunnel"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"gTPTunnel"};
    }
}
