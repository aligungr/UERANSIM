package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_CancelledCellsInTAI_NR extends NGAP_SequenceOf<NGAP_CancelledCellsInTAI_NR_Item> {

    public NGAP_CancelledCellsInTAI_NR() {
        super();
    }

    public NGAP_CancelledCellsInTAI_NR(List<NGAP_CancelledCellsInTAI_NR_Item> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "CancelledCellsInTAI-NR";
    }

    @Override
    public String getXmlTagName() {
        return "CancelledCellsInTAI-NR";
    }

    @Override
    public Class<NGAP_CancelledCellsInTAI_NR_Item> getItemType() {
        return NGAP_CancelledCellsInTAI_NR_Item.class;
    }
}
