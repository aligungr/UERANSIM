package tr.havelsan.ueransim.ngap4.ies.choices;

import tr.havelsan.ueransim.ngap4.core.NgapChoice;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_GTPTunnel;

public class NGAP_UPTransportLayerInformation extends NgapChoice {

    public NGAP_GTPTunnel gTPTunnel;

    @Override
    protected String getAsnName() {
        return "UPTransportLayerInformation";
    }

    @Override
    protected String getXmlTagName() {
        return "UPTransportLayerInformation";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"gTPTunnel"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"gTPTunnel"};
    }
}
