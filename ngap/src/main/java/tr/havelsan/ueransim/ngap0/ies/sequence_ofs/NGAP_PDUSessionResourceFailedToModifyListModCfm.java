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
