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

public class NGAP_PDUSessionResourceFailedToSetupListSURes extends NGAP_SequenceOf<NGAP_PDUSessionResourceFailedToSetupItemSURes> {

    public NGAP_PDUSessionResourceFailedToSetupListSURes() {
        super();
    }

    public NGAP_PDUSessionResourceFailedToSetupListSURes(List<NGAP_PDUSessionResourceFailedToSetupItemSURes> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceFailedToSetupListSURes";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceFailedToSetupListSURes";
    }

    @Override
    public Class<NGAP_PDUSessionResourceFailedToSetupItemSURes> getItemType() {
        return NGAP_PDUSessionResourceFailedToSetupItemSURes.class;
    }
}
