/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_CSI_RS_Resource_Mobility__frequencyDomainAllocation;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_CSI_RS_Resource_Mobility__slotConfig;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_CSI_RS_Index;

public class RRC_CSI_RS_Resource_Mobility extends RRC_Sequence {

    public RRC_CSI_RS_Index csi_RS_Index;
    public RRC_CSI_RS_Resource_Mobility__slotConfig slotConfig;
    public RRC_CSI_RS_Resource_Mobility__associatedSSB associatedSSB;
    public RRC_CSI_RS_Resource_Mobility__frequencyDomainAllocation frequencyDomainAllocation;
    public RRC_Integer firstOFDMSymbolInTimeDomain;
    public RRC_Integer sequenceGenerationConfig;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "csi-RS-Index","slotConfig","associatedSSB","frequencyDomainAllocation","firstOFDMSymbolInTimeDomain","sequenceGenerationConfig" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "csi_RS_Index","slotConfig","associatedSSB","frequencyDomainAllocation","firstOFDMSymbolInTimeDomain","sequenceGenerationConfig" };
    }

    @Override
    public String getAsnName() {
        return "CSI-RS-Resource-Mobility";
    }

    @Override
    public String getXmlTagName() {
        return "CSI-RS-Resource-Mobility";
    }

}
