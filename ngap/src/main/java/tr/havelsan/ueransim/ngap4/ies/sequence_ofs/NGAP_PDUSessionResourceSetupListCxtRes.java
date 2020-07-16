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

public class NGAP_PDUSessionResourceSetupListCxtRes extends NgapSequenceOf<NGAP_PDUSessionResourceSetupItemCxtRes> {

    public NGAP_PDUSessionResourceSetupListCxtRes() {
        super();
    }

    public NGAP_PDUSessionResourceSetupListCxtRes(List<NGAP_PDUSessionResourceSetupItemCxtRes> value) {
        super(value);
    }

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceSetupListCxtRes";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceSetupListCxtRes";
    }

    @Override
    public Class<NGAP_PDUSessionResourceSetupItemCxtRes> getItemType() {
        return NGAP_PDUSessionResourceSetupItemCxtRes.class;
    }
}
