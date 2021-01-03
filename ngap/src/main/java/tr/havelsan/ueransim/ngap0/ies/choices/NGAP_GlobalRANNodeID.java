/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.choices;

import tr.havelsan.ueransim.ngap0.core.NGAP_Choice;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_GlobalGNB_ID;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_GlobalN3IWF_ID;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_GlobalNgENB_ID;

public class NGAP_GlobalRANNodeID extends NGAP_Choice {

    public NGAP_GlobalGNB_ID globalGNB_ID;
    public NGAP_GlobalNgENB_ID globalNgENB_ID;
    public NGAP_GlobalN3IWF_ID globalN3IWF_ID;

    @Override
    public String getAsnName() {
        return "GlobalRANNodeID";
    }

    @Override
    public String getXmlTagName() {
        return "GlobalRANNodeID";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"globalGNB-ID", "globalNgENB-ID", "globalN3IWF-ID"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"globalGNB_ID", "globalNgENB_ID", "globalN3IWF_ID"};
    }
}
