/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_Alpha;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_P0_PUSCH_AlphaSetId;

public class RRC_P0_PUSCH_AlphaSet extends RRC_Sequence {

    public RRC_P0_PUSCH_AlphaSetId p0_PUSCH_AlphaSetId;
    public RRC_Integer p0;
    public RRC_Alpha alpha;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "p0-PUSCH-AlphaSetId","p0","alpha" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "p0_PUSCH_AlphaSetId","p0","alpha" };
    }

    @Override
    public String getAsnName() {
        return "P0-PUSCH-AlphaSet";
    }

    @Override
    public String getXmlTagName() {
        return "P0-PUSCH-AlphaSet";
    }

}
