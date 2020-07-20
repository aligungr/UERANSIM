package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_CancelledCellsInEAI_EUTRA extends NGAP_SequenceOf<NGAP_CancelledCellsInEAI_EUTRA_Item> {

    public NGAP_CancelledCellsInEAI_EUTRA() {
        super();
    }

    public NGAP_CancelledCellsInEAI_EUTRA(List<NGAP_CancelledCellsInEAI_EUTRA_Item> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "CancelledCellsInEAI-EUTRA";
    }

    @Override
    public String getXmlTagName() {
        return "CancelledCellsInEAI-EUTRA";
    }

    @Override
    public Class<NGAP_CancelledCellsInEAI_EUTRA_Item> getItemType() {
        return NGAP_CancelledCellsInEAI_EUTRA_Item.class;
    }
}
