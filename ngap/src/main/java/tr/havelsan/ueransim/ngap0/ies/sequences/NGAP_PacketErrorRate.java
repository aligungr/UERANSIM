/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.NGAP_Integer;
import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;

public class NGAP_PacketErrorRate extends NGAP_Sequence {

    public NGAP_Integer pERScalar;
    public NGAP_Integer pERExponent;

    @Override
    public String getAsnName() {
        return "PacketErrorRate";
    }

    @Override
    public String getXmlTagName() {
        return "PacketErrorRate";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"pERScalar", "pERExponent"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"pERScalar", "pERExponent"};
    }
}
