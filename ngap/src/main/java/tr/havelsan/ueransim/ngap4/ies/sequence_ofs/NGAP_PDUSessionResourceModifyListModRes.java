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

public class NGAP_PDUSessionResourceModifyListModRes extends NgapSequenceOf<NGAP_PDUSessionResourceModifyItemModRes> {

    public NGAP_PDUSessionResourceModifyListModRes() {
        super();
    }

    public NGAP_PDUSessionResourceModifyListModRes(List<NGAP_PDUSessionResourceModifyItemModRes> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceModifyListModRes";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceModifyListModRes";
    }

    @Override
    public Class<NGAP_PDUSessionResourceModifyItemModRes> getItemType() {
        return NGAP_PDUSessionResourceModifyItemModRes.class;
    }
}
