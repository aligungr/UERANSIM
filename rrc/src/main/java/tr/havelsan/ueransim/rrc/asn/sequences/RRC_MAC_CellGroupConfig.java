/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnBoolean;
import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnNull;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_DataInactivityTimer;

public class RRC_MAC_CellGroupConfig extends AsnSequence {
    public RRC_SetupRelease_DRX_Config drx_Config; // optional
    public RRC_SchedulingRequestConfig schedulingRequestConfig; // optional
    public RRC_BSR_Config bsr_Config; // optional
    public RRC_TAG_Config tag_Config; // optional
    public RRC_SetupRelease_PHR_Config phr_Config; // optional
    public AsnBoolean skipUplinkTxDynamic; // mandatory
    public RRC_ext1_40 ext1; // optional

    public static class RRC_SetupRelease_DRX_Config extends AsnChoice {
        public AsnNull release;
        public RRC_DRX_Config setup;
    }

    public static class RRC_ext1_40 extends AsnSequence {
        public AsnBoolean csi_Mask; // optional
        public RRC_SetupRelease_DataInactivityTimer dataInactivityTimer; // optional
    
        public static class RRC_SetupRelease_DataInactivityTimer extends AsnChoice {
            public AsnNull release;
            public RRC_DataInactivityTimer setup;
        }
    }

    public static class RRC_SetupRelease_PHR_Config extends AsnChoice {
        public AsnNull release;
        public RRC_PHR_Config setup;
    }
}

