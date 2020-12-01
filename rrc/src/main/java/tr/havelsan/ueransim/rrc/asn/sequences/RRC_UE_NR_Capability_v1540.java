/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_UE_NR_Capability_v1540 extends RRC_Sequence {

    public RRC_SDAP_Parameters sdap_Parameters;
    public RRC_Integer overheatingInd;
    public RRC_IMS_Parameters ims_Parameters;
    public RRC_UE_NR_CapabilityAddFRX_Mode_v1540 fr1_Add_UE_NR_Capabilities_v1540;
    public RRC_UE_NR_CapabilityAddFRX_Mode_v1540 fr2_Add_UE_NR_Capabilities_v1540;
    public RRC_UE_NR_CapabilityAddFRX_Mode fr1_fr2_Add_UE_NR_Capabilities;
    public RRC_UE_NR_Capability_v1550 nonCriticalExtension;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "sdap-Parameters","overheatingInd","ims-Parameters","fr1-Add-UE-NR-Capabilities-v1540","fr2-Add-UE-NR-Capabilities-v1540","fr1-fr2-Add-UE-NR-Capabilities","nonCriticalExtension" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "sdap_Parameters","overheatingInd","ims_Parameters","fr1_Add_UE_NR_Capabilities_v1540","fr2_Add_UE_NR_Capabilities_v1540","fr1_fr2_Add_UE_NR_Capabilities","nonCriticalExtension" };
    }

    @Override
    public String getAsnName() {
        return "UE-NR-Capability-v1540";
    }

    @Override
    public String getXmlTagName() {
        return "UE-NR-Capability-v1540";
    }

}
