package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.choices.NGAP_CPTransportLayerInformation;
import tr.havelsan.ueransim.ngap4.ies.choices.NGAP_Cause;

public class NGAP_TNLAssociationItem extends NgapSequence {

    public NGAP_CPTransportLayerInformation tNLAssociationAddress;
    public NGAP_Cause cause;

    @Override
    protected String getAsnName() {
        return "TNLAssociationItem";
    }

    @Override
    protected String getXmlTagName() {
        return "TNLAssociationItem";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"tNLAssociationAddress", "cause"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"tNLAssociationAddress", "cause"};
    }
}
