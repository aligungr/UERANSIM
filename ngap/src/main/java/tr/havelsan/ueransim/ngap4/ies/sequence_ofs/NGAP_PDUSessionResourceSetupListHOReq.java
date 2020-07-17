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

public class NGAP_PDUSessionResourceSetupListHOReq extends NGAP_SequenceOf<NGAP_PDUSessionResourceSetupItemHOReq> {

    public NGAP_PDUSessionResourceSetupListHOReq() {
        super();
    }

    public NGAP_PDUSessionResourceSetupListHOReq(List<NGAP_PDUSessionResourceSetupItemHOReq> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceSetupListHOReq";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceSetupListHOReq";
    }

    @Override
    public Class<NGAP_PDUSessionResourceSetupItemHOReq> getItemType() {
        return NGAP_PDUSessionResourceSetupItemHOReq.class;
    }
}
