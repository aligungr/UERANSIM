/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;
import tr.havelsan.ueransim.ngap0.ies.choices.NGAP_UPTransportLayerInformation;

public class NGAP_UPTransportLayerInformationItem extends NGAP_Sequence {

    public NGAP_UPTransportLayerInformation nGU_UP_TNLInformation;

    @Override
    public String getAsnName() {
        return "UPTransportLayerInformationItem";
    }

    @Override
    public String getXmlTagName() {
        return "UPTransportLayerInformationItem";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"nGU-UP-TNLInformation"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"nGU_UP_TNLInformation"};
    }
}
