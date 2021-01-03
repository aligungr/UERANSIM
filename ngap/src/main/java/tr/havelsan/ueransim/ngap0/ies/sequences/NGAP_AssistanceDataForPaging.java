/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;

public class NGAP_AssistanceDataForPaging extends NGAP_Sequence {

    public NGAP_AssistanceDataForRecommendedCells assistanceDataForRecommendedCells;
    public NGAP_PagingAttemptInformation pagingAttemptInformation;

    @Override
    public String getAsnName() {
        return "AssistanceDataForPaging";
    }

    @Override
    public String getXmlTagName() {
        return "AssistanceDataForPaging";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"assistanceDataForRecommendedCells", "pagingAttemptInformation"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"assistanceDataForRecommendedCells", "pagingAttemptInformation"};
    }
}
