/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.ngap0.ies.enumerations;

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

public class NGAP_PDUSessionType extends NGAP_Enumerated {

    public static final NGAP_PDUSessionType IPV4 = new NGAP_PDUSessionType("ipv4");
    public static final NGAP_PDUSessionType IPV6 = new NGAP_PDUSessionType("ipv6");
    public static final NGAP_PDUSessionType IPV4V6 = new NGAP_PDUSessionType("ipv4v6");
    public static final NGAP_PDUSessionType ETHERNET = new NGAP_PDUSessionType("ethernet");
    public static final NGAP_PDUSessionType UNSTRUCTURED = new NGAP_PDUSessionType("unstructured");

    protected NGAP_PDUSessionType(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionType";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionType";
    }
}
