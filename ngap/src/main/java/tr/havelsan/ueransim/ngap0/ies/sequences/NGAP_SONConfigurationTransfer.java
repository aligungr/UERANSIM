/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;
import tr.havelsan.ueransim.ngap0.ies.choices.NGAP_SONInformation;

public class NGAP_SONConfigurationTransfer extends NGAP_Sequence {

    public NGAP_TargetRANNodeID targetRANNodeID;
    public NGAP_SourceRANNodeID sourceRANNodeID;
    public NGAP_SONInformation sONInformation;
    public NGAP_XnTNLConfigurationInfo xnTNLConfigurationInfo;

    @Override
    public String getAsnName() {
        return "SONConfigurationTransfer";
    }

    @Override
    public String getXmlTagName() {
        return "SONConfigurationTransfer";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"targetRANNodeID", "sourceRANNodeID", "sONInformation", "xnTNLConfigurationInfo"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"targetRANNodeID", "sourceRANNodeID", "sONInformation", "xnTNLConfigurationInfo"};
    }
}
