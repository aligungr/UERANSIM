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

public class NGAP_UE_NGAP_ID_pair extends NGAP_Sequence {

    public NGAP_AMF_UE_NGAP_ID aMF_UE_NGAP_ID;
    public NGAP_RAN_UE_NGAP_ID rAN_UE_NGAP_ID;

    @Override
    public String getAsnName() {
        return "UE-NGAP-ID-pair";
    }

    @Override
    public String getXmlTagName() {
        return "UE-NGAP-ID-pair";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"aMF-UE-NGAP-ID", "rAN-UE-NGAP-ID"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"aMF_UE_NGAP_ID", "rAN_UE_NGAP_ID"};
    }
}
