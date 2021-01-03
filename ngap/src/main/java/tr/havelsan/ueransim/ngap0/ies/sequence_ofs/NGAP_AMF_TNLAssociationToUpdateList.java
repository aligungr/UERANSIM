/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_AMF_TNLAssociationToUpdateItem;

import java.util.List;

public class NGAP_AMF_TNLAssociationToUpdateList extends NGAP_SequenceOf<NGAP_AMF_TNLAssociationToUpdateItem> {

    public NGAP_AMF_TNLAssociationToUpdateList() {
        super();
    }

    public NGAP_AMF_TNLAssociationToUpdateList(List<NGAP_AMF_TNLAssociationToUpdateItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "AMF-TNLAssociationToUpdateList";
    }

    @Override
    public String getXmlTagName() {
        return "AMF-TNLAssociationToUpdateList";
    }

    @Override
    public Class<NGAP_AMF_TNLAssociationToUpdateItem> getItemType() {
        return NGAP_AMF_TNLAssociationToUpdateItem.class;
    }
}
