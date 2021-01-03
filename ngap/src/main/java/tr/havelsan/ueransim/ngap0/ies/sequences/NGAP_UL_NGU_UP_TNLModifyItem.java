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

public class NGAP_UL_NGU_UP_TNLModifyItem extends NGAP_Sequence {

    public NGAP_UPTransportLayerInformation uL_NGU_UP_TNLInformation;
    public NGAP_UPTransportLayerInformation dL_NGU_UP_TNLInformation;

    @Override
    public String getAsnName() {
        return "UL-NGU-UP-TNLModifyItem";
    }

    @Override
    public String getXmlTagName() {
        return "UL-NGU-UP-TNLModifyItem";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"uL-NGU-UP-TNLInformation", "dL-NGU-UP-TNLInformation"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"uL_NGU_UP_TNLInformation", "dL_NGU_UP_TNLInformation"};
    }
}
