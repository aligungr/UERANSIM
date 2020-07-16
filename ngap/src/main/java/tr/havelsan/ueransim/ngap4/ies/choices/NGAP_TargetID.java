package tr.havelsan.ueransim.ngap4.ies.choices;

import tr.havelsan.ueransim.ngap4.core.NgapChoice;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_TargetRANNodeID;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_TargeteNB_ID;

public class NGAP_TargetID extends NgapChoice {

    public NGAP_TargetRANNodeID targetRANNodeID;
    public NGAP_TargeteNB_ID targeteNB_ID;

    @Override
    protected String getAsnName() {
        return "TargetID";
    }

    @Override
    protected String getXmlTagName() {
        return "TargetID";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"targetRANNodeID", "targeteNB-ID"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"targetRANNodeID", "targeteNB_ID"};
    }
}
