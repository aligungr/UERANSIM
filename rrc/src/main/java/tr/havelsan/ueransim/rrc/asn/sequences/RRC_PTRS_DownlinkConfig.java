/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_PTRS_DownlinkConfig__frequencyDensity;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_PTRS_DownlinkConfig__timeDensity;

public class RRC_PTRS_DownlinkConfig extends RRC_Sequence {

    public RRC_PTRS_DownlinkConfig__frequencyDensity frequencyDensity;
    public RRC_PTRS_DownlinkConfig__timeDensity timeDensity;
    public RRC_Integer epre_Ratio;
    public RRC_Integer resourceElementOffset;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "frequencyDensity","timeDensity","epre-Ratio","resourceElementOffset" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "frequencyDensity","timeDensity","epre_Ratio","resourceElementOffset" };
    }

    @Override
    public String getAsnName() {
        return "PTRS-DownlinkConfig";
    }

    @Override
    public String getXmlTagName() {
        return "PTRS-DownlinkConfig";
    }

}
