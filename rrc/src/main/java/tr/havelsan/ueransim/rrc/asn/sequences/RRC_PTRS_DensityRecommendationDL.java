/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_PTRS_DensityRecommendationDL extends RRC_Sequence {

    public RRC_Integer frequencyDensity1;
    public RRC_Integer frequencyDensity2;
    public RRC_Integer timeDensity1;
    public RRC_Integer timeDensity2;
    public RRC_Integer timeDensity3;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "frequencyDensity1","frequencyDensity2","timeDensity1","timeDensity2","timeDensity3" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "frequencyDensity1","frequencyDensity2","timeDensity1","timeDensity2","timeDensity3" };
    }

    @Override
    public String getAsnName() {
        return "PTRS-DensityRecommendationDL";
    }

    @Override
    public String getXmlTagName() {
        return "PTRS-DensityRecommendationDL";
    }

}
