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

public class NGAP_PriorityLevelQos extends NGAP_Integer {

    public NGAP_PriorityLevelQos(long value) {
        super(value);
    }

    public NGAP_PriorityLevelQos(Octet value) {
        super(value);
    }

    public NGAP_PriorityLevelQos(Octet2 value) {
        super(value);
    }

    public NGAP_PriorityLevelQos(Octet3 value) {
        super(value);
    }

    public NGAP_PriorityLevelQos(Octet4 value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PriorityLevelQos";
    }

    @Override
    public String getXmlTagName() {
        return "PriorityLevelQos";
    }
}
