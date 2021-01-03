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
import tr.havelsan.ueransim.ngap0.ies.choices.NGAP_Cause;

public class NGAP_TNLAssociationItem extends NGAP_Sequence {

    public NGAP_CPTransportLayerInformation tNLAssociationAddress;
    public NGAP_Cause cause;

    @Override
    public String getAsnName() {
        return "TNLAssociationItem";
    }

    @Override
    public String getXmlTagName() {
        return "TNLAssociationItem";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"tNLAssociationAddress", "cause"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"tNLAssociationAddress", "cause"};
    }
}
