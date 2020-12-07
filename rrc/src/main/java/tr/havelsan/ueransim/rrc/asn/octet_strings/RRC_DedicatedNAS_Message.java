/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
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

