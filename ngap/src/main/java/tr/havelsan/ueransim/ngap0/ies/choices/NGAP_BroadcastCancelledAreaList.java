/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.choices;

import tr.havelsan.ueransim.ngap0.core.NGAP_Choice;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.*;

public class NGAP_BroadcastCancelledAreaList extends NGAP_Choice {

    public NGAP_CellIDCancelledEUTRA cellIDCancelledEUTRA;
    public NGAP_TAICancelledEUTRA tAICancelledEUTRA;
    public NGAP_EmergencyAreaIDCancelledEUTRA emergencyAreaIDCancelledEUTRA;
    public NGAP_CellIDCancelledNR cellIDCancelledNR;
    public NGAP_TAICancelledNR tAICancelledNR;
    public NGAP_EmergencyAreaIDCancelledNR emergencyAreaIDCancelledNR;

    @Override
    public String getAsnName() {
        return "BroadcastCancelledAreaList";
    }

    @Override
    public String getXmlTagName() {
        return "BroadcastCancelledAreaList";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"cellIDCancelledEUTRA", "tAICancelledEUTRA", "emergencyAreaIDCancelledEUTRA", "cellIDCancelledNR", "tAICancelledNR", "emergencyAreaIDCancelledNR"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"cellIDCancelledEUTRA", "tAICancelledEUTRA", "emergencyAreaIDCancelledEUTRA", "cellIDCancelledNR", "tAICancelledNR", "emergencyAreaIDCancelledNR"};
    }
}
