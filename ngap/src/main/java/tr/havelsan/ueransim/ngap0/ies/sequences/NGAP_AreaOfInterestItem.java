/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_LocationReportingReferenceID;

public class NGAP_AreaOfInterestItem extends NGAP_Sequence {

    public NGAP_AreaOfInterest areaOfInterest;
    public NGAP_LocationReportingReferenceID locationReportingReferenceID;

    @Override
    public String getAsnName() {
        return "AreaOfInterestItem";
    }

    @Override
    public String getXmlTagName() {
        return "AreaOfInterestItem";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"areaOfInterest", "locationReportingReferenceID"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"areaOfInterest", "locationReportingReferenceID"};
    }
}
