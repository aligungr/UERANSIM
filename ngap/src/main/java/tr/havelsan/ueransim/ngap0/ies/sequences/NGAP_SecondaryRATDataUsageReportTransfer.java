/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;

public class NGAP_SecondaryRATDataUsageReportTransfer extends NGAP_Sequence {

    public NGAP_SecondaryRATUsageInformation secondaryRATUsageInformation;

    @Override
    public String getAsnName() {
        return "SecondaryRATDataUsageReportTransfer";
    }

    @Override
    public String getXmlTagName() {
        return "SecondaryRATDataUsageReportTransfer";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"secondaryRATUsageInformation"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"secondaryRATUsageInformation"};
    }
}
