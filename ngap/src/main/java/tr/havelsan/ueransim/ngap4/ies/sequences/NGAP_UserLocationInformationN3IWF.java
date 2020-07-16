package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.bit_strings.NGAP_TransportLayerAddress;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.NGAP_PortNumber;

public class NGAP_UserLocationInformationN3IWF extends NgapSequence {

    public NGAP_TransportLayerAddress iPAddress;
    public NGAP_PortNumber portNumber;

    @Override
    protected String getAsnName() {
        return "UserLocationInformationN3IWF";
    }

    @Override
    protected String getXmlTagName() {
        return "UserLocationInformationN3IWF";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"iPAddress", "portNumber"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"iPAddress", "portNumber"};
    }
}
