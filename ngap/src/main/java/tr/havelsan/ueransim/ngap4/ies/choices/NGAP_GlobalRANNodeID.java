package tr.havelsan.ueransim.ngap4.ies.choices;

import tr.havelsan.ueransim.ngap4.core.NgapChoice;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_GlobalGNB_ID;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_GlobalN3IWF_ID;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_GlobalNgENB_ID;

public class NGAP_GlobalRANNodeID extends NgapChoice {

    public NGAP_GlobalGNB_ID globalGNB_ID;
    public NGAP_GlobalNgENB_ID globalNgENB_ID;
    public NGAP_GlobalN3IWF_ID globalN3IWF_ID;

    @Override
    protected String getAsnName() {
        return "GlobalRANNodeID";
    }

    @Override
    protected String getXmlTagName() {
        return "GlobalRANNodeID";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"globalGNB-ID", "globalNgENB-ID", "globalN3IWF-ID"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"globalGNB_ID", "globalNgENB_ID", "globalN3IWF_ID"};
    }
}
