package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.*;
import tr.havelsan.ueransim.ngap4.pdu.*;
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

public class NGAP_PDUSessionResourceModifyListModCfm extends NGAP_SequenceOf<NGAP_PDUSessionResourceModifyItemModCfm> {

    public NGAP_PDUSessionResourceModifyListModCfm() {
        super();
    }

    public NGAP_PDUSessionResourceModifyListModCfm(List<NGAP_PDUSessionResourceModifyItemModCfm> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceModifyListModCfm";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceModifyListModCfm";
    }

    @Override
    public Class<NGAP_PDUSessionResourceModifyItemModCfm> getItemType() {
        return NGAP_PDUSessionResourceModifyItemModCfm.class;
    }
}
