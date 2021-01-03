/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.enumerations;

import tr.havelsan.ueransim.ngap0.core.NGAP_Enumerated;

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
