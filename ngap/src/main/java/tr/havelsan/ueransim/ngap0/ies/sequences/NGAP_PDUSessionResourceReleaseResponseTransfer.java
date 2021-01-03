/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;

public class NGAP_PDUSessionResourceReleaseResponseTransfer extends NGAP_Sequence {


    @Override
    public String getAsnName() {
        return "PDUSessionResourceReleaseResponseTransfer";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceReleaseResponseTransfer";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{};
    }
}
