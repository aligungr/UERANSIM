/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.ngap0.core;

import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.Octet2;
import tr.havelsan.ueransim.utils.octets.Octet3;
import tr.havelsan.ueransim.utils.octets.Octet4;

public class NGAP_Integer extends NGAP_Value {

    public long value;

    public NGAP_Integer(long value) {
        this.value = value;
    }

    public NGAP_Integer(Octet value) {
        this(value.longValue());
    }

    public NGAP_Integer(Octet2 value) {
        this(value.longValue());
    }

    public NGAP_Integer(Octet3 value) {
        this(value.longValue());
    }

    public NGAP_Integer(Octet4 value) {
        this(value.longValue());
    }

    @Override
    public String getAsnName() {
        return "INTEGER";
    }

    @Override
    public String getXmlTagName() {
        return "INTEGER";
    }
}
