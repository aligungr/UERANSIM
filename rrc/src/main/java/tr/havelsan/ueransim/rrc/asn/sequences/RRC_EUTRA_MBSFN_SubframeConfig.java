/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_EUTRA_MBSFN_SubframeConfig__subframeAllocation1;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_EUTRA_MBSFN_SubframeConfig__subframeAllocation2;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_EUTRA_MBSFN_SubframeConfig extends RRC_Sequence {

    public RRC_Integer radioframeAllocationPeriod;
    public RRC_Integer radioframeAllocationOffset;
    public RRC_EUTRA_MBSFN_SubframeConfig__subframeAllocation1 subframeAllocation1;
    public RRC_EUTRA_MBSFN_SubframeConfig__subframeAllocation2 subframeAllocation2;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "radioframeAllocationPeriod","radioframeAllocationOffset","subframeAllocation1","subframeAllocation2" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "radioframeAllocationPeriod","radioframeAllocationOffset","subframeAllocation1","subframeAllocation2" };
    }

    @Override
    public String getAsnName() {
        return "EUTRA-MBSFN-SubframeConfig";
    }

    @Override
    public String getXmlTagName() {
        return "EUTRA-MBSFN-SubframeConfig";
    }

}
