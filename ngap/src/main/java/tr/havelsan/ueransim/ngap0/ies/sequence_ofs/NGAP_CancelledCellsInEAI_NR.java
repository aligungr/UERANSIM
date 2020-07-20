package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_CancelledCellsInEAI_NR extends NGAP_SequenceOf<NGAP_CancelledCellsInEAI_NR_Item> {

    public NGAP_CancelledCellsInEAI_NR() {
        super();
    }

    public NGAP_CancelledCellsInEAI_NR(List<NGAP_CancelledCellsInEAI_NR_Item> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "CancelledCellsInEAI-NR";
    }

    @Override
    public String getXmlTagName() {
        return "CancelledCellsInEAI-NR";
    }

    @Override
    public Class<NGAP_CancelledCellsInEAI_NR_Item> getItemType() {
        return NGAP_CancelledCellsInEAI_NR_Item.class;
    }
}
