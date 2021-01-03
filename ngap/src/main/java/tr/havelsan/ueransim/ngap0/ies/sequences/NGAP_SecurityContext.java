/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;
import tr.havelsan.ueransim.ngap0.ies.bit_strings.NGAP_SecurityKey;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_NextHopChainingCount;

public class NGAP_SecurityContext extends NGAP_Sequence {

    public NGAP_NextHopChainingCount nextHopChainingCount;
    public NGAP_SecurityKey nextHopNH;

    @Override
    public String getAsnName() {
        return "SecurityContext";
    }

    @Override
    public String getXmlTagName() {
        return "SecurityContext";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"nextHopChainingCount", "nextHopNH"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"nextHopChainingCount", "nextHopNH"};
    }
}
