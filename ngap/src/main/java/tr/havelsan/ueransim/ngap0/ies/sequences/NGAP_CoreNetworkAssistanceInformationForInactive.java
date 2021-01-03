/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;
import tr.havelsan.ueransim.ngap0.ies.bit_strings.NGAP_PeriodicRegistrationUpdateTimer;
import tr.havelsan.ueransim.ngap0.ies.choices.NGAP_UEIdentityIndexValue;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_MICOModeIndication;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_PagingDRX;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_TAIListForInactive;

public class NGAP_CoreNetworkAssistanceInformationForInactive extends NGAP_Sequence {

    public NGAP_UEIdentityIndexValue uEIdentityIndexValue;
    public NGAP_PagingDRX uESpecificDRX;
    public NGAP_PeriodicRegistrationUpdateTimer periodicRegistrationUpdateTimer;
    public NGAP_MICOModeIndication mICOModeIndication;
    public NGAP_TAIListForInactive tAIListForInactive;
    public NGAP_ExpectedUEBehaviour expectedUEBehaviour;

    @Override
    public String getAsnName() {
        return "CoreNetworkAssistanceInformationForInactive";
    }

    @Override
    public String getXmlTagName() {
        return "CoreNetworkAssistanceInformationForInactive";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"uEIdentityIndexValue", "uESpecificDRX", "periodicRegistrationUpdateTimer", "mICOModeIndication", "tAIListForInactive", "expectedUEBehaviour"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"uEIdentityIndexValue", "uESpecificDRX", "periodicRegistrationUpdateTimer", "mICOModeIndication", "tAIListForInactive", "expectedUEBehaviour"};
    }
}
