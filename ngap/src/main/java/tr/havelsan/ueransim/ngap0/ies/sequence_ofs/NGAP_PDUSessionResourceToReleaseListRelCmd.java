/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_PDUSessionResourceToReleaseItemRelCmd;

import java.util.List;

public class NGAP_PDUSessionResourceToReleaseListRelCmd extends NGAP_SequenceOf<NGAP_PDUSessionResourceToReleaseItemRelCmd> {

    public NGAP_PDUSessionResourceToReleaseListRelCmd() {
        super();
    }

    public NGAP_PDUSessionResourceToReleaseListRelCmd(List<NGAP_PDUSessionResourceToReleaseItemRelCmd> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceToReleaseListRelCmd";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceToReleaseListRelCmd";
    }

    @Override
    public Class<NGAP_PDUSessionResourceToReleaseItemRelCmd> getItemType() {
        return NGAP_PDUSessionResourceToReleaseItemRelCmd.class;
    }
}
