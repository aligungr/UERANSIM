package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;

public class NGAP_TargeteNB_ID extends NgapSequence {

    public NGAP_GlobalNgENB_ID globalENB_ID;
    public NGAP_EPS_TAI selected_EPS_TAI;

    @Override
    protected String getAsnName() {
        return "TargeteNB-ID";
    }

    @Override
    protected String getXmlTagName() {
        return "TargeteNB-ID";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"globalENB-ID", "selected-EPS-TAI"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"globalENB_ID", "selected_EPS_TAI"};
    }
}
