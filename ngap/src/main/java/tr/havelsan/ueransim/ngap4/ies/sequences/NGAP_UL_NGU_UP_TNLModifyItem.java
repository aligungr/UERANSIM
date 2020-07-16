package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.choices.NGAP_UPTransportLayerInformation;

public class NGAP_UL_NGU_UP_TNLModifyItem extends NgapSequence {

    public NGAP_UPTransportLayerInformation uL_NGU_UP_TNLInformation;
    public NGAP_UPTransportLayerInformation dL_NGU_UP_TNLInformation;

    @Override
    protected String getAsnName() {
        return "UL-NGU-UP-TNLModifyItem";
    }

    @Override
    protected String getXmlTagName() {
        return "UL-NGU-UP-TNLModifyItem";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"uL-NGU-UP-TNLInformation", "dL-NGU-UP-TNLInformation"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"uL_NGU_UP_TNLInformation", "dL_NGU_UP_TNLInformation"};
    }
}
