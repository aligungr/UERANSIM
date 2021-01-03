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
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_DRB_ID;

public class NGAP_DataForwardingResponseDRBItem extends NGAP_Sequence {

    public NGAP_DRB_ID dRB_ID;
    public NGAP_UPTransportLayerInformation dLForwardingUP_TNLInformation;
    public NGAP_UPTransportLayerInformation uLForwardingUP_TNLInformation;

    @Override
    public String getAsnName() {
        return "DataForwardingResponseDRBItem";
    }

    @Override
    public String getXmlTagName() {
        return "DataForwardingResponseDRBItem";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"dRB-ID", "dLForwardingUP-TNLInformation", "uLForwardingUP-TNLInformation"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"dRB_ID", "dLForwardingUP_TNLInformation", "uLForwardingUP_TNLInformation"};
    }
}
