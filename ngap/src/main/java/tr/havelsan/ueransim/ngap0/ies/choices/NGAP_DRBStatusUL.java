/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.choices;

import tr.havelsan.ueransim.ngap0.core.NGAP_Choice;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_DRBStatusUL12;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_DRBStatusUL18;

public class NGAP_DRBStatusUL extends NGAP_Choice {

    public NGAP_DRBStatusUL12 dRBStatusUL12;
    public NGAP_DRBStatusUL18 dRBStatusUL18;

    @Override
    public String getAsnName() {
        return "DRBStatusUL";
    }

    @Override
    public String getXmlTagName() {
        return "DRBStatusUL";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"dRBStatusUL12", "dRBStatusUL18"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"dRBStatusUL12", "dRBStatusUL18"};
    }
}
