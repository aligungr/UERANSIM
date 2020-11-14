/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.ngap0.ies.integers;

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

public class NGAP_AMF_UE_NGAP_ID extends NGAP_Integer {

    public NGAP_AMF_UE_NGAP_ID(long value) {
        super(value);
    }

    public NGAP_AMF_UE_NGAP_ID(Octet value) {
        super(value);
    }

    public NGAP_AMF_UE_NGAP_ID(Octet2 value) {
        super(value);
    }

    public NGAP_AMF_UE_NGAP_ID(Octet3 value) {
        super(value);
    }

    public NGAP_AMF_UE_NGAP_ID(Octet4 value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "AMF-UE-NGAP-ID";
    }

    @Override
    public String getXmlTagName() {
        return "AMF-UE-NGAP-ID";
    }
}
