/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_UE_NR_Capability_v1540 extends AsnSequence {
    public RRC_SDAP_Parameters sdap_Parameters; // optional
    public RRC_overheatingInd overheatingInd; // optional
    public RRC_IMS_Parameters ims_Parameters; // optional
    public RRC_UE_NR_CapabilityAddFRX_Mode_v1540 fr1_Add_UE_NR_Capabilities_v1540; // optional
    public RRC_UE_NR_CapabilityAddFRX_Mode_v1540 fr2_Add_UE_NR_Capabilities_v1540; // optional
    public RRC_UE_NR_CapabilityAddFRX_Mode fr1_fr2_Add_UE_NR_Capabilities; // optional
    public RRC_UE_NR_Capability_v1550 nonCriticalExtension; // optional

    public static class RRC_overheatingInd extends AsnEnumerated {
        public static final RRC_overheatingInd SUPPORTED = new RRC_overheatingInd(0);
    
        private RRC_overheatingInd(long value) {
            super(value);
        }
    }
}

