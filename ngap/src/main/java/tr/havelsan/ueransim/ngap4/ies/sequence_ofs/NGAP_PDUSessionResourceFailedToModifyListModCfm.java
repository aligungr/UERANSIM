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

public class NGAP_PDUSessionResourceFailedToModifyListModCfm extends NGAP_SequenceOf<NGAP_PDUSessionResourceFailedToModifyItemModCfm> {

    public NGAP_PDUSessionResourceFailedToModifyListModCfm() {
        super();
    }

    public NGAP_PDUSessionResourceFailedToModifyListModCfm(List<NGAP_PDUSessionResourceFailedToModifyItemModCfm> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceFailedToModifyListModCfm";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceFailedToModifyListModCfm";
    }

    @Override
    public Class<NGAP_PDUSessionResourceFailedToModifyItemModCfm> getItemType() {
        return NGAP_PDUSessionResourceFailedToModifyItemModCfm.class;
    }
}
