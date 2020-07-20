package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.bit_strings.*;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.*;

public class NGAP_UserLocationInformationN3IWF extends NGAP_Sequence {

    public NGAP_TransportLayerAddress iPAddress;
    public NGAP_PortNumber portNumber;

    @Override
    public String getAsnName() {
        return "UserLocationInformationN3IWF";
    }

    @Override
    public String getXmlTagName() {
        return "UserLocationInformationN3IWF";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"iPAddress", "portNumber"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"iPAddress", "portNumber"};
    }
}
