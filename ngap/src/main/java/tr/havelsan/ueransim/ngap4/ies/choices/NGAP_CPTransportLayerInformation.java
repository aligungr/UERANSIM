package tr.havelsan.ueransim.ngap4.ies.choices;

import tr.havelsan.ueransim.ngap4.core.NgapChoice;
import tr.havelsan.ueransim.ngap4.ies.bit_strings.NGAP_TransportLayerAddress;

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
