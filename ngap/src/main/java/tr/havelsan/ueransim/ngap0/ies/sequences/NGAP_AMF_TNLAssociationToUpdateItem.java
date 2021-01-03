/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;
import tr.havelsan.ueransim.ngap0.ies.choices.NGAP_CPTransportLayerInformation;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_TNLAssociationUsage;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_TNLAddressWeightFactor;

public class NGAP_AMF_TNLAssociationToUpdateItem extends NGAP_Sequence {

    public NGAP_CPTransportLayerInformation aMF_TNLAssociationAddress;
    public NGAP_TNLAssociationUsage tNLAssociationUsage;
    public NGAP_TNLAddressWeightFactor tNLAddressWeightFactor;

    @Override
    public String getAsnName() {
        return "AMF-TNLAssociationToUpdateItem";
    }

    @Override
    public String getXmlTagName() {
        return "AMF-TNLAssociationToUpdateItem";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"aMF-TNLAssociationAddress", "tNLAssociationUsage", "tNLAddressWeightFactor"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"aMF_TNLAssociationAddress", "tNLAssociationUsage", "tNLAddressWeightFactor"};
    }
}
