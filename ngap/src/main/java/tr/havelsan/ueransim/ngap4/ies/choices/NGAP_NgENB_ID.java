package tr.havelsan.ueransim.ngap4.ies.choices;

import tr.havelsan.ueransim.ngap4.core.NgapBitString;
import tr.havelsan.ueransim.ngap4.core.NgapChoice;

public class NGAP_NgENB_ID extends NgapChoice {

    public NgapBitString macroNgENB_ID;
    public NgapBitString shortMacroNgENB_ID;
    public NgapBitString longMacroNgENB_ID;

    @Override
    protected String getAsnName() {
        return "NgENB-ID";
    }

    @Override
    protected String getXmlTagName() {
        return "NgENB-ID";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"macroNgENB-ID", "shortMacroNgENB-ID", "longMacroNgENB-ID"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"macroNgENB_ID", "shortMacroNgENB_ID", "longMacroNgENB_ID"};
    }
}
