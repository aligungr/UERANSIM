/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;
import tr.havelsan.ueransim.ngap0.ies.choices.NGAP_GlobalRANNodeID;

public class NGAP_AreaOfInterestRANNodeItem extends NGAP_Sequence {

    public NGAP_GlobalRANNodeID globalRANNodeID;

    @Override
    public String getAsnName() {
        return "AreaOfInterestRANNodeItem";
    }

    @Override
    public String getXmlTagName() {
        return "AreaOfInterestRANNodeItem";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"globalRANNodeID"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"globalRANNodeID"};
    }
}
