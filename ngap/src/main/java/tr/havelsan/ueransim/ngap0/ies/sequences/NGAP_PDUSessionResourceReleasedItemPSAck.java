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

public class NGAP_PDUSessionResourceReleasedItemPSAck extends NGAP_Sequence {

    public NGAP_PDUSessionID pDUSessionID;
    public NGAP_OctetString pathSwitchRequestUnsuccessfulTransfer;

    @Override
    public String getAsnName() {
        return "PDUSessionResourceReleasedItemPSAck";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceReleasedItemPSAck";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"pDUSessionID", "pathSwitchRequestUnsuccessfulTransfer"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"pDUSessionID", "pathSwitchRequestUnsuccessfulTransfer"};
    }
}
