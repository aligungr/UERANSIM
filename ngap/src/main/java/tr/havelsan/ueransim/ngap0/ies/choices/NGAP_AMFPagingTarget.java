/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.choices;

import tr.havelsan.ueransim.ngap0.core.NGAP_Choice;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_TAI;

public class NGAP_AMFPagingTarget extends NGAP_Choice {

    public NGAP_GlobalRANNodeID globalRANNodeID;
    public NGAP_TAI tAI;

    @Override
    public String getAsnName() {
        return "AMFPagingTarget";
    }

    @Override
    public String getXmlTagName() {
        return "AMFPagingTarget";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"globalRANNodeID", "tAI"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"globalRANNodeID", "tAI"};
    }
}
