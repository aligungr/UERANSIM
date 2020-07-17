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

public class NGAP_PDUSessionResourceFailedToSetupListSURes extends NgapSequenceOf<NGAP_PDUSessionResourceFailedToSetupItemSURes> {

    public NGAP_PDUSessionResourceFailedToSetupListSURes() {
        super();
    }

    public NGAP_PDUSessionResourceFailedToSetupListSURes(List<NGAP_PDUSessionResourceFailedToSetupItemSURes> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceFailedToSetupListSURes";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceFailedToSetupListSURes";
    }

    @Override
    public Class<NGAP_PDUSessionResourceFailedToSetupItemSURes> getItemType() {
        return NGAP_PDUSessionResourceFailedToSetupItemSURes.class;
    }
}
