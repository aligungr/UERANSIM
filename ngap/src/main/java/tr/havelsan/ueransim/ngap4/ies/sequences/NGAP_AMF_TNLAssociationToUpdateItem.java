package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.choices.NGAP_CPTransportLayerInformation;
import tr.havelsan.ueransim.ngap4.ies.enumerations.NGAP_TNLAssociationUsage;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_TNLAddressWeightFactor;

public class NGAP_AMF_TNLAssociationToUpdateItem extends NgapSequence {

    public NGAP_CPTransportLayerInformation aMF_TNLAssociationAddress;
    public NGAP_TNLAssociationUsage tNLAssociationUsage;
    public NGAP_TNLAddressWeightFactor tNLAddressWeightFactor;

    @Override
    protected String getAsnName() {
        return "AMF-TNLAssociationToUpdateItem";
    }

    @Override
    protected String getXmlTagName() {
        return "AMF-TNLAssociationToUpdateItem";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"aMF-TNLAssociationAddress", "tNLAssociationUsage", "tNLAddressWeightFactor"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"aMF_TNLAssociationAddress", "tNLAssociationUsage", "tNLAddressWeightFactor"};
    }
}
