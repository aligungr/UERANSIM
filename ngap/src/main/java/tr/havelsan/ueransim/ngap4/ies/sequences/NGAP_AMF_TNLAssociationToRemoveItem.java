package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.choices.NGAP_CPTransportLayerInformation;

public class NGAP_AMF_TNLAssociationToRemoveItem extends NgapSequence {

    public NGAP_CPTransportLayerInformation aMF_TNLAssociationAddress;

    @Override
    protected String getAsnName() {
        return "AMF-TNLAssociationToRemoveItem";
    }

    @Override
    protected String getXmlTagName() {
        return "AMF-TNLAssociationToRemoveItem";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"aMF-TNLAssociationAddress"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"aMF_TNLAssociationAddress"};
    }
}
