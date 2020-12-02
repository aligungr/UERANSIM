/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_PTRS_UplinkConfig__transformPrecoderDisabled__frequencyDensity;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_PTRS_UplinkConfig__transformPrecoderDisabled__timeDensity;

public class RRC_PTRS_UplinkConfig__transformPrecoderDisabled extends RRC_Sequence {

    public RRC_PTRS_UplinkConfig__transformPrecoderDisabled__frequencyDensity frequencyDensity;
    public RRC_PTRS_UplinkConfig__transformPrecoderDisabled__timeDensity timeDensity;
    public RRC_Integer maxNrofPorts;
    public RRC_Integer resourceElementOffset;
    public RRC_Integer ptrs_Power;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "frequencyDensity","timeDensity","maxNrofPorts","resourceElementOffset","ptrs-Power" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "frequencyDensity","timeDensity","maxNrofPorts","resourceElementOffset","ptrs_Power" };
    }

}
