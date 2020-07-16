package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_UL_NGU_UP_TNLModifyItem;

public class NGAP_UL_NGU_UP_TNLModifyList extends NgapSequenceOf<NGAP_UL_NGU_UP_TNLModifyItem> {

    @Override
    protected String getAsnName() {
        return "UL-NGU-UP-TNLModifyList";
    }

    @Override
    protected String getXmlTagName() {
        return "UL-NGU-UP-TNLModifyList";
    }

    @Override
    public Class<NGAP_UL_NGU_UP_TNLModifyItem> getItemType() {
        return NGAP_UL_NGU_UP_TNLModifyItem.class;
    }
}
