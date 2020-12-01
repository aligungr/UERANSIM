/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_CSI_IM_Resource__csi_IM_ResourceElementPattern;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_CSI_ResourcePeriodicityAndOffset;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_CSI_IM_ResourceId;

public class RRC_CSI_IM_Resource extends RRC_Sequence {

    public RRC_CSI_IM_ResourceId csi_IM_ResourceId;
    public RRC_CSI_IM_Resource__csi_IM_ResourceElementPattern csi_IM_ResourceElementPattern;
    public RRC_CSI_FrequencyOccupation freqBand;
    public RRC_CSI_ResourcePeriodicityAndOffset periodicityAndOffset;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "csi-IM-ResourceId","csi-IM-ResourceElementPattern","freqBand","periodicityAndOffset" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "csi_IM_ResourceId","csi_IM_ResourceElementPattern","freqBand","periodicityAndOffset" };
    }

    @Override
    public String getAsnName() {
        return "CSI-IM-Resource";
    }

    @Override
    public String getXmlTagName() {
        return "CSI-IM-Resource";
    }

}
