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
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_TriggeringMessage;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_ProcedureCode;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_CriticalityDiagnostics_IE_List;

public class NGAP_CriticalityDiagnostics extends NGAP_Sequence {

    public NGAP_ProcedureCode procedureCode;
    public NGAP_TriggeringMessage triggeringMessage;
    public NGAP_Criticality procedureCriticality;
    public NGAP_CriticalityDiagnostics_IE_List iEsCriticalityDiagnostics;

    @Override
    public String getAsnName() {
        return "CriticalityDiagnostics";
    }

    @Override
    public String getXmlTagName() {
        return "CriticalityDiagnostics";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"procedureCode", "triggeringMessage", "procedureCriticality", "iEsCriticalityDiagnostics"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"procedureCode", "triggeringMessage", "procedureCriticality", "iEsCriticalityDiagnostics"};
    }
}
