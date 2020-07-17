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

public class NGAP_PDUSessionResourceListCxtRelCpl extends NgapSequenceOf<NGAP_PDUSessionResourceItemCxtRelCpl> {

    public NGAP_PDUSessionResourceListCxtRelCpl() {
        super();
    }

    public NGAP_PDUSessionResourceListCxtRelCpl(List<NGAP_PDUSessionResourceItemCxtRelCpl> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceListCxtRelCpl";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceListCxtRelCpl";
    }

    @Override
    public Class<NGAP_PDUSessionResourceItemCxtRelCpl> getItemType() {
        return NGAP_PDUSessionResourceItemCxtRelCpl.class;
    }
}
