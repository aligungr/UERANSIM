package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_CriticalityDiagnostics_IE_Item;

public class NGAP_CriticalityDiagnostics_IE_List extends NgapSequenceOf<NGAP_CriticalityDiagnostics_IE_Item> {

    @Override
    protected String getAsnName() {
        return "CriticalityDiagnostics-IE-List";
    }

    @Override
    protected String getXmlTagName() {
        return "CriticalityDiagnostics-IE-List";
    }

    @Override
    public Class<NGAP_CriticalityDiagnostics_IE_Item> getItemType() {
        return NGAP_CriticalityDiagnostics_IE_Item.class;
    }
}
