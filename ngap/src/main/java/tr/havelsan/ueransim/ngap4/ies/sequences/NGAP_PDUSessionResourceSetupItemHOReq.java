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

public class NGAP_PDUSessionResourceSetupItemHOReq extends NgapSequence {

    public NGAP_PDUSessionID pDUSessionID;
    public NGAP_S_NSSAI s_NSSAI;
    public NgapOctetString handoverRequestTransfer;

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceSetupItemHOReq";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceSetupItemHOReq";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"pDUSessionID", "s-NSSAI", "handoverRequestTransfer"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"pDUSessionID", "s_NSSAI", "handoverRequestTransfer"};
    }
}
