package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.choices.*;

public class NGAP_AMF_TNLAssociationToRemoveItem extends NGAP_Sequence {

    public NGAP_CPTransportLayerInformation aMF_TNLAssociationAddress;

    @Override
    public String getAsnName() {
        return "AMF-TNLAssociationToRemoveItem";
    }

    @Override
    public String getXmlTagName() {
        return "AMF-TNLAssociationToRemoveItem";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"aMF-TNLAssociationAddress"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"aMF_TNLAssociationAddress"};
    }
}
