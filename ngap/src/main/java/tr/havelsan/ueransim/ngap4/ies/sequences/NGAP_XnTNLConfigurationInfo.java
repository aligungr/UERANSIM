package tr.havelsan.ueransim.ngap4.ies.sequences;

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

public class NGAP_XnTNLConfigurationInfo extends NGAP_Sequence {

    public NGAP_XnTLAs xnTransportLayerAddresses;
    public NGAP_XnExtTLAs xnExtendedTransportLayerAddresses;

    @Override
    public String getAsnName() {
        return "XnTNLConfigurationInfo";
    }

    @Override
    public String getXmlTagName() {
        return "XnTNLConfigurationInfo";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"xnTransportLayerAddresses", "xnExtendedTransportLayerAddresses"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"xnTransportLayerAddresses", "xnExtendedTransportLayerAddresses"};
    }
}
