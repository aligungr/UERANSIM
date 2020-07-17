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

public class NGAP_PDUSessionResourceFailedToModifyListModRes extends NgapSequenceOf<NGAP_PDUSessionResourceFailedToModifyItemModRes> {

    public NGAP_PDUSessionResourceFailedToModifyListModRes() {
        super();
    }

    public NGAP_PDUSessionResourceFailedToModifyListModRes(List<NGAP_PDUSessionResourceFailedToModifyItemModRes> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceFailedToModifyListModRes";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceFailedToModifyListModRes";
    }

    @Override
    public Class<NGAP_PDUSessionResourceFailedToModifyItemModRes> getItemType() {
        return NGAP_PDUSessionResourceFailedToModifyItemModRes.class;
    }
}
