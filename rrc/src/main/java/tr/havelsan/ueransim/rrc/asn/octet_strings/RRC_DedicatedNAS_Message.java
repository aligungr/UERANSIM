/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.octet_strings;

import tr.havelsan.ueransim.asn.core.AsnOctetString;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class RRC_DedicatedNAS_Message extends AsnOctetString {
    public RRC_DedicatedNAS_Message() {
    }
    
    public RRC_DedicatedNAS_Message(OctetString value) {
        super(value);
    }
}

