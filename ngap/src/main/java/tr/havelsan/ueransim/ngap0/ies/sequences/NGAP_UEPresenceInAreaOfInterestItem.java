/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_UEPresence;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_LocationReportingReferenceID;

public class NGAP_UEPresenceInAreaOfInterestItem extends NGAP_Sequence {

    public NGAP_LocationReportingReferenceID locationReportingReferenceID;
    public NGAP_UEPresence uEPresence;

    @Override
    public String getAsnName() {
        return "UEPresenceInAreaOfInterestItem";
    }

    @Override
    public String getXmlTagName() {
        return "UEPresenceInAreaOfInterestItem";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"locationReportingReferenceID", "uEPresence"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"locationReportingReferenceID", "uEPresence"};
    }
}
