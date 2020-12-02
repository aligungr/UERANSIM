/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_RangeToBestCell;

public class RRC_SIB2__cellReselectionInfoCommon extends RRC_Sequence {

    public RRC_Integer nrofSS_BlocksToAverage;
    public RRC_ThresholdNR absThreshSS_BlocksConsolidation;
    public RRC_RangeToBestCell rangeToBestCell;
    public RRC_Integer q_Hyst;
    public RRC_SIB2__cellReselectionInfoCommon__speedStateReselectionPars speedStateReselectionPars;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "nrofSS-BlocksToAverage","absThreshSS-BlocksConsolidation","rangeToBestCell","q-Hyst","speedStateReselectionPars" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "nrofSS_BlocksToAverage","absThreshSS_BlocksConsolidation","rangeToBestCell","q_Hyst","speedStateReselectionPars" };
    }

}
