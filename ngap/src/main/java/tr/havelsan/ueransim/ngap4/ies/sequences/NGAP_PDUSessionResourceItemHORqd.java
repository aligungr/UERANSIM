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

public class NGAP_PDUSessionResourceItemHORqd extends NgapSequence {

    public NGAP_PDUSessionID pDUSessionID;
    public NgapOctetString handoverRequiredTransfer;

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceItemHORqd";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceItemHORqd";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"pDUSessionID", "handoverRequiredTransfer"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"pDUSessionID", "handoverRequiredTransfer"};
    }
}
