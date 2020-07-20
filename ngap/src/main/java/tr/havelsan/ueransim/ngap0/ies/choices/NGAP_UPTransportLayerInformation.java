package tr.havelsan.ueransim.ngap0.ies.choices;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

public class NGAP_UPTransportLayerInformation extends NGAP_Choice {

    public NGAP_GTPTunnel gTPTunnel;

    @Override
    public String getAsnName() {
        return "UPTransportLayerInformation";
    }

    @Override
    public String getXmlTagName() {
        return "UPTransportLayerInformation";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"gTPTunnel"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"gTPTunnel"};
    }
}
