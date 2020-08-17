package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.pdu.*;
import tr.havelsan.ueransim.utils.bits.*;
import tr.havelsan.ueransim.utils.octets.*;
import tr.havelsan.ueransim.ngap0.ies.bit_strings.*;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.*;
import tr.havelsan.ueransim.ngap0.ies.printable_strings.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.*;
import tr.havelsan.ueransim.ngap0.ies.choices.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;
import tr.havelsan.ueransim.ngap0.ies.enumerations.*;

import java.util.List;

public class NGAP_PDUSessionResourceModifyIndicationTransfer extends NGAP_Sequence {

    public NGAP_UP_TNLInformation dL_UP_TNLInformation;

    @Override
    public String getAsnName() {
        return "PDUSessionResourceModifyIndicationTransfer";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceModifyIndicationTransfer";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"dL-UP-TNLInformation"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"dL_UP_TNLInformation"};
    }
}
