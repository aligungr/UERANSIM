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

public class NGAP_PDUSessionResourceFailedToSetupListHOAck extends NGAP_SequenceOf<NGAP_PDUSessionResourceFailedToSetupItemHOAck> {

    public NGAP_PDUSessionResourceFailedToSetupListHOAck() {
        super();
    }

    public NGAP_PDUSessionResourceFailedToSetupListHOAck(List<NGAP_PDUSessionResourceFailedToSetupItemHOAck> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceFailedToSetupListHOAck";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceFailedToSetupListHOAck";
    }

    @Override
    public Class<NGAP_PDUSessionResourceFailedToSetupItemHOAck> getItemType() {
        return NGAP_PDUSessionResourceFailedToSetupItemHOAck.class;
    }
}
