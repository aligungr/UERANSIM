/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_FreqBandList;

public class RRC_UE_CapabilityRequestFilterNR extends RRC_Sequence {

    public RRC_FreqBandList frequencyBandListFilter;
    public RRC_UE_CapabilityRequestFilterNR_v1540 nonCriticalExtension;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "frequencyBandListFilter","nonCriticalExtension" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "frequencyBandListFilter","nonCriticalExtension" };
    }

    @Override
    public String getAsnName() {
        return "UE-CapabilityRequestFilterNR";
    }

    @Override
    public String getXmlTagName() {
        return "UE-CapabilityRequestFilterNR";
    }

}
