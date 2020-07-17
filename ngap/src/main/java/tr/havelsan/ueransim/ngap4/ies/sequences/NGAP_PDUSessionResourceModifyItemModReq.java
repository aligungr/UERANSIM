package tr.havelsan.ueransim.ngap4.ies.sequences;

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

public class NGAP_PDUSessionResourceModifyItemModReq extends NGAP_Sequence {

    public NGAP_PDUSessionID pDUSessionID;
    public NGAP_NAS_PDU nAS_PDU;
    public NGAP_OctetString pDUSessionResourceModifyRequestTransfer;

    @Override
    public String getAsnName() {
        return "PDUSessionResourceModifyItemModReq";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceModifyItemModReq";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"pDUSessionID", "nAS-PDU", "pDUSessionResourceModifyRequestTransfer"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"pDUSessionID", "nAS_PDU", "pDUSessionResourceModifyRequestTransfer"};
    }
}
