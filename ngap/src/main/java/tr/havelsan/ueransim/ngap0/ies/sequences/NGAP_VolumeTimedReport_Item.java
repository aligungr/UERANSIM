/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.NGAP_Integer;
import tr.havelsan.ueransim.ngap0.core.NGAP_OctetString;
import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;

public class NGAP_VolumeTimedReport_Item extends NGAP_Sequence {

    public NGAP_OctetString startTimeStamp;
    public NGAP_OctetString endTimeStamp;
    public NGAP_Integer usageCountUL;
    public NGAP_Integer usageCountDL;

    @Override
    public String getAsnName() {
        return "VolumeTimedReport-Item";
    }

    @Override
    public String getXmlTagName() {
        return "VolumeTimedReport-Item";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"startTimeStamp", "endTimeStamp", "usageCountUL", "usageCountDL"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"startTimeStamp", "endTimeStamp", "usageCountUL", "usageCountDL"};
    }
}
