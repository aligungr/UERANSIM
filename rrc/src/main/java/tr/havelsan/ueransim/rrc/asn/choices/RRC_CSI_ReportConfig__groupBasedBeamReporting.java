/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Null;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_CSI_ReportConfig__groupBasedBeamReporting__disabled;

public class RRC_CSI_ReportConfig__groupBasedBeamReporting extends RRC_Choice {

    public RRC_Null enabled;
    public RRC_CSI_ReportConfig__groupBasedBeamReporting__disabled disabled;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "enabled","disabled" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "enabled","disabled" };
    }

}
