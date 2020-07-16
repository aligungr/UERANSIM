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

public class NGAP_PDUSessionResourceSetupListSURes extends NgapSequenceOf<NGAP_PDUSessionResourceSetupItemSURes> {

    public NGAP_PDUSessionResourceSetupListSURes() {
        super();
    }

    public NGAP_PDUSessionResourceSetupListSURes(List<NGAP_PDUSessionResourceSetupItemSURes> value) {
        super(value);
    }

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceSetupListSURes";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceSetupListSURes";
    }

    @Override
    public Class<NGAP_PDUSessionResourceSetupItemSURes> getItemType() {
        return NGAP_PDUSessionResourceSetupItemSURes.class;
    }
}
