/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_DL_AM_RLC;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_DL_UM_RLC;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_UL_AM_RLC;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_UL_UM_RLC;

public class RRC_RLC_Config extends AsnChoice {
    public RRC_am am;
    public RRC_um_Bi_Directional um_Bi_Directional;
    public RRC_um_Uni_Directional_UL um_Uni_Directional_UL;
    public RRC_um_Uni_Directional_DL um_Uni_Directional_DL;

    public static class RRC_um_Bi_Directional extends AsnSequence {
        public RRC_UL_UM_RLC ul_UM_RLC; // mandatory
        public RRC_DL_UM_RLC dl_UM_RLC; // mandatory
    }

    public static class RRC_um_Uni_Directional_UL extends AsnSequence {
        public RRC_UL_UM_RLC ul_UM_RLC; // mandatory
    }

    public static class RRC_am extends AsnSequence {
        public RRC_UL_AM_RLC ul_AM_RLC; // mandatory
        public RRC_DL_AM_RLC dl_AM_RLC; // mandatory
    }

    public static class RRC_um_Uni_Directional_DL extends AsnSequence {
        public RRC_DL_UM_RLC dl_UM_RLC; // mandatory
    }
}

