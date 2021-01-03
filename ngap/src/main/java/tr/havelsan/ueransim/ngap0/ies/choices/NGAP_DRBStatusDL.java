/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.choices;

import tr.havelsan.ueransim.ngap0.core.NGAP_Choice;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_DRBStatusDL12;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_DRBStatusDL18;

public class NGAP_DRBStatusDL extends NGAP_Choice {

    public NGAP_DRBStatusDL12 dRBStatusDL12;
    public NGAP_DRBStatusDL18 dRBStatusDL18;

    @Override
    public String getAsnName() {
        return "DRBStatusDL";
    }

    @Override
    public String getXmlTagName() {
        return "DRBStatusDL";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"dRBStatusDL12", "dRBStatusDL18"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"dRBStatusDL12", "dRBStatusDL18"};
    }
}
