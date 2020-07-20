package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;

public class NGAP_TargeteNB_ID extends NGAP_Sequence {

    public NGAP_GlobalNgENB_ID globalENB_ID;
    public NGAP_EPS_TAI selected_EPS_TAI;

    @Override
    public String getAsnName() {
        return "TargeteNB-ID";
    }

    @Override
    public String getXmlTagName() {
        return "TargeteNB-ID";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"globalENB-ID", "selected-EPS-TAI"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"globalENB_ID", "selected_EPS_TAI"};
    }
}
