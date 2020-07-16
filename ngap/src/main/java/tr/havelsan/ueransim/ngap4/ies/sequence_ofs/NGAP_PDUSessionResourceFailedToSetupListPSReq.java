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

public class NGAP_PDUSessionResourceFailedToSetupListPSReq extends NgapSequenceOf<NGAP_PDUSessionResourceFailedToSetupItemPSReq> {

    public NGAP_PDUSessionResourceFailedToSetupListPSReq() {
        super();
    }

    public NGAP_PDUSessionResourceFailedToSetupListPSReq(List<NGAP_PDUSessionResourceFailedToSetupItemPSReq> value) {
        super(value);
    }

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceFailedToSetupListPSReq";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceFailedToSetupListPSReq";
    }

    @Override
    public Class<NGAP_PDUSessionResourceFailedToSetupItemPSReq> getItemType() {
        return NGAP_PDUSessionResourceFailedToSetupItemPSReq.class;
    }
}
