/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_CSI_RS_ResourceMapping__density;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_CSI_RS_ResourceMapping__frequencyDomainAllocation;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_CSI_RS_ResourceMapping extends RRC_Sequence {

    public RRC_CSI_RS_ResourceMapping__frequencyDomainAllocation frequencyDomainAllocation;
    public RRC_Integer nrofPorts;
    public RRC_Integer firstOFDMSymbolInTimeDomain;
    public RRC_Integer firstOFDMSymbolInTimeDomain2;
    public RRC_Integer cdm_Type;
    public RRC_CSI_RS_ResourceMapping__density density;
    public RRC_CSI_FrequencyOccupation freqBand;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "frequencyDomainAllocation","nrofPorts","firstOFDMSymbolInTimeDomain","firstOFDMSymbolInTimeDomain2","cdm-Type","density","freqBand" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "frequencyDomainAllocation","nrofPorts","firstOFDMSymbolInTimeDomain","firstOFDMSymbolInTimeDomain2","cdm_Type","density","freqBand" };
    }

    @Override
    public String getAsnName() {
        return "CSI-RS-ResourceMapping";
    }

    @Override
    public String getXmlTagName() {
        return "CSI-RS-ResourceMapping";
    }

}
