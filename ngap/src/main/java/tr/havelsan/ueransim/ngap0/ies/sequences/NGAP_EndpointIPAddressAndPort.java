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

public class NGAP_EndpointIPAddressAndPort extends NGAP_Sequence {

    public NGAP_TransportLayerAddress endpointIPAddress;
    public NGAP_PortNumber portNumber;

    @Override
    public String getAsnName() {
        return "EndpointIPAddressAndPort";
    }

    @Override
    public String getXmlTagName() {
        return "EndpointIPAddressAndPort";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"endpointIPAddress", "portNumber"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"endpointIPAddress", "portNumber"};
    }
}
