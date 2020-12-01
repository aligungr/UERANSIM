/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_MobilityStateParameters extends RRC_Sequence {

    public RRC_Integer t_Evaluation;
    public RRC_Integer t_HystNormal;
    public RRC_Integer n_CellChangeMedium;
    public RRC_Integer n_CellChangeHigh;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "t-Evaluation","t-HystNormal","n-CellChangeMedium","n-CellChangeHigh" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "t_Evaluation","t_HystNormal","n_CellChangeMedium","n_CellChangeHigh" };
    }

    @Override
    public String getAsnName() {
        return "MobilityStateParameters";
    }

    @Override
    public String getXmlTagName() {
        return "MobilityStateParameters";
    }

}
