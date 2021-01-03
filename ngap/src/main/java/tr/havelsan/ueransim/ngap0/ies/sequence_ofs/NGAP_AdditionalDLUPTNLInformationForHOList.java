/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_AdditionalDLUPTNLInformationForHOItem;

import java.util.List;

public class NGAP_AdditionalDLUPTNLInformationForHOList extends NGAP_SequenceOf<NGAP_AdditionalDLUPTNLInformationForHOItem> {

    public NGAP_AdditionalDLUPTNLInformationForHOList() {
        super();
    }

    public NGAP_AdditionalDLUPTNLInformationForHOList(List<NGAP_AdditionalDLUPTNLInformationForHOItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "AdditionalDLUPTNLInformationForHOList";
    }

    @Override
    public String getXmlTagName() {
        return "AdditionalDLUPTNLInformationForHOList";
    }

    @Override
    public Class<NGAP_AdditionalDLUPTNLInformationForHOItem> getItemType() {
        return NGAP_AdditionalDLUPTNLInformationForHOItem.class;
    }
}
