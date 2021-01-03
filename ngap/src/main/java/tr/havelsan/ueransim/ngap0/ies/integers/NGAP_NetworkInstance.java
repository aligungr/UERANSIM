/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.integers;

import tr.havelsan.ueransim.ngap0.core.NGAP_Integer;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.Octet2;
import tr.havelsan.ueransim.utils.octets.Octet3;
import tr.havelsan.ueransim.utils.octets.Octet4;

public class NGAP_NetworkInstance extends NGAP_Integer {

    public NGAP_NetworkInstance(long value) {
        super(value);
    }

    public NGAP_NetworkInstance(Octet value) {
        super(value);
    }

    public NGAP_NetworkInstance(Octet2 value) {
        super(value);
    }

    public NGAP_NetworkInstance(Octet3 value) {
        super(value);
    }

    public NGAP_NetworkInstance(Octet4 value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "NetworkInstance";
    }

    @Override
    public String getXmlTagName() {
        return "NetworkInstance";
    }
}
