/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Boolean;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_SchedulingRequestId;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_LogicalChannelConfig__ul_SpecificParameters__allowedSCS_List;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_LogicalChannelConfig__ul_SpecificParameters__allowedServingCells;

public class RRC_LogicalChannelConfig__ul_SpecificParameters extends RRC_Sequence {

    public RRC_Integer priority;
    public RRC_Integer prioritisedBitRate;
    public RRC_Integer bucketSizeDuration;
    public RRC_LogicalChannelConfig__ul_SpecificParameters__allowedServingCells allowedServingCells;
    public RRC_LogicalChannelConfig__ul_SpecificParameters__allowedSCS_List allowedSCS_List;
    public RRC_Integer maxPUSCH_Duration;
    public RRC_Integer configuredGrantType1Allowed;
    public RRC_Integer logicalChannelGroup;
    public RRC_SchedulingRequestId schedulingRequestID;
    public RRC_Boolean logicalChannelSR_Mask;
    public RRC_Boolean logicalChannelSR_DelayTimerApplied;
    public RRC_Integer bitRateQueryProhibitTimer;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "priority","prioritisedBitRate","bucketSizeDuration","allowedServingCells","allowedSCS-List","maxPUSCH-Duration","configuredGrantType1Allowed","logicalChannelGroup","schedulingRequestID","logicalChannelSR-Mask","logicalChannelSR-DelayTimerApplied","bitRateQueryProhibitTimer" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "priority","prioritisedBitRate","bucketSizeDuration","allowedServingCells","allowedSCS_List","maxPUSCH_Duration","configuredGrantType1Allowed","logicalChannelGroup","schedulingRequestID","logicalChannelSR_Mask","logicalChannelSR_DelayTimerApplied","bitRateQueryProhibitTimer" };
    }

}
