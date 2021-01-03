/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_PDUSessionResourceToReleaseItemHOCmd;

import java.util.List;

public class NGAP_PDUSessionResourceToReleaseListHOCmd extends NGAP_SequenceOf<NGAP_PDUSessionResourceToReleaseItemHOCmd> {

    public NGAP_PDUSessionResourceToReleaseListHOCmd() {
        super();
    }

    public NGAP_PDUSessionResourceToReleaseListHOCmd(List<NGAP_PDUSessionResourceToReleaseItemHOCmd> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceToReleaseListHOCmd";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceToReleaseListHOCmd";
    }

    @Override
    public Class<NGAP_PDUSessionResourceToReleaseItemHOCmd> getItemType() {
        return NGAP_PDUSessionResourceToReleaseItemHOCmd.class;
    }
}
