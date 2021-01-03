/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_Criticality;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_TypeOfError;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_ProtocolIE_ID;

public class NGAP_CriticalityDiagnostics_IE_Item extends NGAP_Sequence {

    public NGAP_Criticality iECriticality;
    public NGAP_ProtocolIE_ID iE_ID;
    public NGAP_TypeOfError typeOfError;

    @Override
    public String getAsnName() {
        return "CriticalityDiagnostics-IE-Item";
    }

    @Override
    public String getXmlTagName() {
        return "CriticalityDiagnostics-IE-Item";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"iECriticality", "iE-ID", "typeOfError"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"iECriticality", "iE_ID", "typeOfError"};
    }
}
