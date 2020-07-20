package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_UEHistoryInformation extends NGAP_SequenceOf<NGAP_LastVisitedCellItem> {

    public NGAP_UEHistoryInformation() {
        super();
    }

    public NGAP_UEHistoryInformation(List<NGAP_LastVisitedCellItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "UEHistoryInformation";
    }

    @Override
    public String getXmlTagName() {
        return "UEHistoryInformation";
    }

    @Override
    public Class<NGAP_LastVisitedCellItem> getItemType() {
        return NGAP_LastVisitedCellItem.class;
    }
}
