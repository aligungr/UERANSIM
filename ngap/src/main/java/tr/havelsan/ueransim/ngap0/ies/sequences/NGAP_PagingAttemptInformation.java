/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_NextPagingAreaScope;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_IntendedNumberOfPagingAttempts;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_PagingAttemptCount;

public class NGAP_PagingAttemptInformation extends NGAP_Sequence {

    public NGAP_PagingAttemptCount pagingAttemptCount;
    public NGAP_IntendedNumberOfPagingAttempts intendedNumberOfPagingAttempts;
    public NGAP_NextPagingAreaScope nextPagingAreaScope;

    @Override
    public String getAsnName() {
        return "PagingAttemptInformation";
    }

    @Override
    public String getXmlTagName() {
        return "PagingAttemptInformation";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"pagingAttemptCount", "intendedNumberOfPagingAttempts", "nextPagingAreaScope"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"pagingAttemptCount", "intendedNumberOfPagingAttempts", "nextPagingAreaScope"};
    }
}
