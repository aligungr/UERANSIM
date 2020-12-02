/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_SchedulingRequestId;

public class RRC_SchedulingRequestConfig__schedulingRequestToReleaseList extends RRC_SequenceOf<RRC_SchedulingRequestId> {

    @Override
    public Class<RRC_SchedulingRequestId> getItemType() {
        return RRC_SchedulingRequestId.class;
    }

}
