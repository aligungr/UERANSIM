package tr.havelsan.ueransim.ngap0.ies.choices;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.bit_strings.*;

public class NGAP_CPTransportLayerInformation extends NGAP_Choice {

    public NGAP_TransportLayerAddress endpointIPAddress;

    @Override
    public String getAsnName() {
        return "CPTransportLayerInformation";
    }

    @Override
    public String getXmlTagName() {
        return "CPTransportLayerInformation";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"endpointIPAddress"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"endpointIPAddress"};
    }
}
