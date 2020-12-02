/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.bit_strings.RRC_NG_5G_S_TMSI;
import tr.havelsan.ueransim.rrc.asn.core.RRC_BitString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;

public class RRC_RRCSetupComplete_IEs__ng_5G_S_TMSI_Value extends RRC_Choice {

    public RRC_NG_5G_S_TMSI ng_5G_S_TMSI;
    public RRC_BitString ng_5G_S_TMSI_Part2;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "ng-5G-S-TMSI","ng-5G-S-TMSI-Part2" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "ng_5G_S_TMSI","ng_5G_S_TMSI_Part2" };
    }

}
