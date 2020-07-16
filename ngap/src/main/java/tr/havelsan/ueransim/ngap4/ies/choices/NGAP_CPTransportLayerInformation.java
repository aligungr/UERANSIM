package tr.havelsan.ueransim.ngap4.ies.choices;

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

public class NGAP_CPTransportLayerInformation extends NgapChoice {

    public NGAP_TransportLayerAddress endpointIPAddress;

    @Override
    protected String getAsnName() {
        return "CPTransportLayerInformation";
    }

    @Override
    protected String getXmlTagName() {
        return "CPTransportLayerInformation";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"endpointIPAddress"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"endpointIPAddress"};
    }
}
