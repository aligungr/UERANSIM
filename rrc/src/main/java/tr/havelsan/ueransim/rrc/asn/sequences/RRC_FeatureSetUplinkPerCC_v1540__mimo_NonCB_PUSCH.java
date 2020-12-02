/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_FeatureSetUplinkPerCC_v1540__mimo_NonCB_PUSCH extends RRC_Sequence {

    public RRC_Integer maxNumberSRS_ResourcePerSet;
    public RRC_Integer maxNumberSimultaneousSRS_ResourceTx;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "maxNumberSRS-ResourcePerSet","maxNumberSimultaneousSRS-ResourceTx" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "maxNumberSRS_ResourcePerSet","maxNumberSimultaneousSRS_ResourceTx" };
    }

}
