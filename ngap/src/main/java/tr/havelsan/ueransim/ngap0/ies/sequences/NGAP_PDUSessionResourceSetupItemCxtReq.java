/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.NGAP_OctetString;
import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;
import tr.havelsan.ueransim.ngap0.ies.integers.NGAP_PDUSessionID;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.NGAP_NAS_PDU;

public class NGAP_PDUSessionResourceSetupItemCxtReq extends NGAP_Sequence {

    public NGAP_PDUSessionID pDUSessionID;
    public NGAP_NAS_PDU nAS_PDU;
    public NGAP_S_NSSAI s_NSSAI;
    public NGAP_OctetString pDUSessionResourceSetupRequestTransfer;

    @Override
    public String getAsnName() {
        return "PDUSessionResourceSetupItemCxtReq";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceSetupItemCxtReq";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"pDUSessionID", "nAS-PDU", "s-NSSAI", "pDUSessionResourceSetupRequestTransfer"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"pDUSessionID", "nAS_PDU", "s_NSSAI", "pDUSessionResourceSetupRequestTransfer"};
    }
}
