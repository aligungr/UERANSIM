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

public class NGAP_DataForwardingResponseDRBItem extends NgapSequence {

    public NGAP_DRB_ID dRB_ID;
    public NGAP_UPTransportLayerInformation dLForwardingUP_TNLInformation;
    public NGAP_UPTransportLayerInformation uLForwardingUP_TNLInformation;

    @Override
    protected String getAsnName() {
        return "DataForwardingResponseDRBItem";
    }

    @Override
    protected String getXmlTagName() {
        return "DataForwardingResponseDRBItem";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"dRB-ID", "dLForwardingUP-TNLInformation", "uLForwardingUP-TNLInformation"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"dRB_ID", "dLForwardingUP_TNLInformation", "uLForwardingUP_TNLInformation"};
    }
}
