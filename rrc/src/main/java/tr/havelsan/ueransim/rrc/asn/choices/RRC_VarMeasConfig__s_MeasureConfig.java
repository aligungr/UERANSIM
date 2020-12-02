/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_RSRP_Range;

public class RRC_VarMeasConfig__s_MeasureConfig extends RRC_Choice {

    public RRC_RSRP_Range ssb_RSRP;
    public RRC_RSRP_Range csi_RSRP;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "ssb-RSRP","csi-RSRP" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "ssb_RSRP","csi_RSRP" };
    }

}
