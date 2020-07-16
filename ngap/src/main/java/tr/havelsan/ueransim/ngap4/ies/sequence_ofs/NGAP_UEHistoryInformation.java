package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_LastVisitedCellItem;

public class NGAP_UEHistoryInformation extends NgapSequenceOf<NGAP_LastVisitedCellItem> {

    @Override
    protected String getAsnName() {
        return "UEHistoryInformation";
    }

    @Override
    protected String getXmlTagName() {
        return "UEHistoryInformation";
    }

    @Override
    public Class<NGAP_LastVisitedCellItem> getItemType() {
        return NGAP_LastVisitedCellItem.class;
    }
}
