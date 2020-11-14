/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

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

public class NGAP_DRBsSubjectToStatusTransferList extends NGAP_SequenceOf<NGAP_DRBsSubjectToStatusTransferItem> {

    public NGAP_DRBsSubjectToStatusTransferList() {
        super();
    }

    public NGAP_DRBsSubjectToStatusTransferList(List<NGAP_DRBsSubjectToStatusTransferItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "DRBsSubjectToStatusTransferList";
    }

    @Override
    public String getXmlTagName() {
        return "DRBsSubjectToStatusTransferList";
    }

    @Override
    public Class<NGAP_DRBsSubjectToStatusTransferItem> getItemType() {
        return NGAP_DRBsSubjectToStatusTransferItem.class;
    }
}
