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

public class NGAP_PDUSessionResourceModifyListModInd extends NgapSequenceOf<NGAP_PDUSessionResourceModifyItemModInd> {

    public NGAP_PDUSessionResourceModifyListModInd() {
        super();
    }

    public NGAP_PDUSessionResourceModifyListModInd(List<NGAP_PDUSessionResourceModifyItemModInd> value) {
        super(value);
    }

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceModifyListModInd";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceModifyListModInd";
    }

    @Override
    public Class<NGAP_PDUSessionResourceModifyItemModInd> getItemType() {
        return NGAP_PDUSessionResourceModifyItemModInd.class;
    }
}
