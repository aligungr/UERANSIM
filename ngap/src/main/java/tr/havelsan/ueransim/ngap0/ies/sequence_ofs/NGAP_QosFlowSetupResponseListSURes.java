/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_QosFlowSetupResponseItemSURes;

import java.util.List;

public class NGAP_QosFlowSetupResponseListSURes extends NGAP_SequenceOf<NGAP_QosFlowSetupResponseItemSURes> {

    public NGAP_QosFlowSetupResponseListSURes() {
        super();
    }

    public NGAP_QosFlowSetupResponseListSURes(List<NGAP_QosFlowSetupResponseItemSURes> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "QosFlowSetupResponseListSURes";
    }

    @Override
    public String getXmlTagName() {
        return "QosFlowSetupResponseListSURes";
    }

    @Override
    public Class<NGAP_QosFlowSetupResponseItemSURes> getItemType() {
        return NGAP_QosFlowSetupResponseItemSURes.class;
    }
}
