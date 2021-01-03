/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.pdu;

import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_Criticality;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_ProcedureCode;

public class NGAP_InitiatingMessage extends NGAP_Sequence {

    public NGAP_ProcedureCode procedureCode;
    public NGAP_Criticality criticality;
    public NGAP_MessageChoice value;

    @Override
    public String[] getMemberNames() {
        return new String[]{"procedureCode", "criticality", "value"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"procedureCode", "criticality", "value"};
    }

    @Override
    public String getAsnName() {
        return "InitiatingMessage";
    }

    @Override
    public String getXmlTagName() {
        return "InitiatingMessage";
    }
}
