/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.NGAP_TAC;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_BroadcastPLMNList;

public class NGAP_SupportedTAItem extends NGAP_Sequence {

    public NGAP_TAC tAC;
    public NGAP_BroadcastPLMNList broadcastPLMNList;

    @Override
    public String getAsnName() {
        return "SupportedTAItem";
    }

    @Override
    public String getXmlTagName() {
        return "SupportedTAItem";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"tAC", "broadcastPLMNList"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"tAC", "broadcastPLMNList"};
    }
}
