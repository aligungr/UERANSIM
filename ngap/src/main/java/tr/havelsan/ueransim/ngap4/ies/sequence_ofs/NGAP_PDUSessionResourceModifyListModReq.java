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

public class NGAP_PDUSessionResourceModifyListModReq extends NGAP_SequenceOf<NGAP_PDUSessionResourceModifyItemModReq> {

    public NGAP_PDUSessionResourceModifyListModReq() {
        super();
    }

    public NGAP_PDUSessionResourceModifyListModReq(List<NGAP_PDUSessionResourceModifyItemModReq> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceModifyListModReq";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceModifyListModReq";
    }

    @Override
    public Class<NGAP_PDUSessionResourceModifyItemModReq> getItemType() {
        return NGAP_PDUSessionResourceModifyItemModReq.class;
    }
}
