/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.NGAP_UERadioCapabilityForPagingOfEUTRA;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.NGAP_UERadioCapabilityForPagingOfNR;

public class NGAP_UERadioCapabilityForPaging extends NGAP_Sequence {

    public NGAP_UERadioCapabilityForPagingOfNR uERadioCapabilityForPagingOfNR;
    public NGAP_UERadioCapabilityForPagingOfEUTRA uERadioCapabilityForPagingOfEUTRA;

    @Override
    public String getAsnName() {
        return "UERadioCapabilityForPaging";
    }

    @Override
    public String getXmlTagName() {
        return "UERadioCapabilityForPaging";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"uERadioCapabilityForPagingOfNR", "uERadioCapabilityForPagingOfEUTRA"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"uERadioCapabilityForPagingOfNR", "uERadioCapabilityForPagingOfEUTRA"};
    }
}
