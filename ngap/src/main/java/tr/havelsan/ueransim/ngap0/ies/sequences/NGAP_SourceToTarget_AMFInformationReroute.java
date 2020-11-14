/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.pdu.*;
import tr.havelsan.ueransim.utils.bits.*;
import tr.havelsan.ueransim.utils.octets.*;
import tr.havelsan.ueransim.ngap0.ies.bit_strings.*;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.*;
import tr.havelsan.ueransim.ngap0.ies.printable_strings.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.*;
import tr.havelsan.ueransim.ngap0.ies.choices.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;
import tr.havelsan.ueransim.ngap0.ies.enumerations.*;

import java.util.List;

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
