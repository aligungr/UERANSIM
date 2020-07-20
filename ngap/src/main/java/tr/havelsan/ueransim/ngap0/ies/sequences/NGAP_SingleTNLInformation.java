package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.choices.*;

public class NGAP_SingleTNLInformation extends NGAP_Sequence {

    public NGAP_UPTransportLayerInformation uPTransportLayerInformation;

    @Override
    public String getAsnName() {
        return "SingleTNLInformation";
    }

    @Override
    public String getXmlTagName() {
        return "SingleTNLInformation";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"uPTransportLayerInformation"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"uPTransportLayerInformation"};
    }
}
