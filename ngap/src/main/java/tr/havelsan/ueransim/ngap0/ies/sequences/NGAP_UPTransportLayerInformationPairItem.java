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

public class NGAP_UPTransportLayerInformationPairItem extends NGAP_Sequence {

    public NGAP_UPTransportLayerInformation uL_NGU_UP_TNLInformation;
    public NGAP_UPTransportLayerInformation dL_NGU_UP_TNLInformation;

    @Override
    public String getAsnName() {
        return "UPTransportLayerInformationPairItem";
    }

    @Override
    public String getXmlTagName() {
        return "UPTransportLayerInformationPairItem";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"uL-NGU-UP-TNLInformation", "dL-NGU-UP-TNLInformation"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"uL_NGU_UP_TNLInformation", "dL_NGU_UP_TNLInformation"};
    }
}
