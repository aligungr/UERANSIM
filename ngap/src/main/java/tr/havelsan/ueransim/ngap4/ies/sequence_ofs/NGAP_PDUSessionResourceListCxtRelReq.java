package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.*;
import tr.havelsan.ueransim.utils.bits.*;
import tr.havelsan.ueransim.utils.octets.*;
import tr.havelsan.ueransim.ngap4.ies.bit_strings.*;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.*;
import tr.havelsan.ueransim.ngap4.ies.printable_strings.*;
import tr.havelsan.ueransim.ngap4.ies.sequences.*;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.*;
import tr.havelsan.ueransim.ngap4.ies.choices.*;
import tr.havelsan.ueransim.ngap4.ies.integers.*;
import tr.havelsan.ueransim.ngap4.ies.enumerations.*;

import java.util.List;

public class NGAP_PDUSessionResourceListCxtRelReq extends NGAP_SequenceOf<NGAP_PDUSessionResourceItemCxtRelReq> {

    public NGAP_PDUSessionResourceListCxtRelReq() {
        super();
    }

    public NGAP_PDUSessionResourceListCxtRelReq(List<NGAP_PDUSessionResourceItemCxtRelReq> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceListCxtRelReq";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceListCxtRelReq";
    }

    @Override
    public Class<NGAP_PDUSessionResourceItemCxtRelReq> getItemType() {
        return NGAP_PDUSessionResourceItemCxtRelReq.class;
    }
}
