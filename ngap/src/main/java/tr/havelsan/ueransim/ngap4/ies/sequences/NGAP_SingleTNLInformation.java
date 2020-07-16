package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.choices.NGAP_UPTransportLayerInformation;

public class NGAP_SingleTNLInformation extends NgapSequence {

    public NGAP_UPTransportLayerInformation uPTransportLayerInformation;

    @Override
    protected String getAsnName() {
        return "SingleTNLInformation";
    }

    @Override
    protected String getXmlTagName() {
        return "SingleTNLInformation";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"uPTransportLayerInformation"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"uPTransportLayerInformation"};
    }
}
