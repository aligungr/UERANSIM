/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_PTRS_UplinkConfig__transformPrecoderEnabled__sampleDensity;

public class RRC_PTRS_UplinkConfig__transformPrecoderEnabled extends RRC_Sequence {

    public RRC_PTRS_UplinkConfig__transformPrecoderEnabled__sampleDensity sampleDensity;
    public RRC_Integer timeDensityTransformPrecoding;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "sampleDensity","timeDensityTransformPrecoding" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "sampleDensity","timeDensityTransformPrecoding" };
    }

}
