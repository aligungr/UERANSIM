/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.NGAP_ConfiguredNSSAI;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.NGAP_RejectedNSSAIinPLMN;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.NGAP_RejectedNSSAIinTA;

public class NGAP_SourceToTarget_AMFInformationReroute extends NGAP_Sequence {

    public NGAP_ConfiguredNSSAI configuredNSSAI;
    public NGAP_RejectedNSSAIinPLMN rejectedNSSAIinPLMN;
    public NGAP_RejectedNSSAIinTA rejectedNSSAIinTA;

    @Override
    public String getAsnName() {
        return "SourceToTarget-AMFInformationReroute";
    }

    @Override
    public String getXmlTagName() {
        return "SourceToTarget-AMFInformationReroute";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"configuredNSSAI", "rejectedNSSAIinPLMN", "rejectedNSSAIinTA"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"configuredNSSAI", "rejectedNSSAIinPLMN", "rejectedNSSAIinTA"};
    }
}
