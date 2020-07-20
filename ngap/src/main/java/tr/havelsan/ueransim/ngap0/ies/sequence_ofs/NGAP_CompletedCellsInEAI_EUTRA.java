package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_CompletedCellsInEAI_EUTRA extends NGAP_SequenceOf<NGAP_CompletedCellsInEAI_EUTRA_Item> {

    public NGAP_CompletedCellsInEAI_EUTRA() {
        super();
    }

    public NGAP_CompletedCellsInEAI_EUTRA(List<NGAP_CompletedCellsInEAI_EUTRA_Item> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "CompletedCellsInEAI-EUTRA";
    }

    @Override
    public String getXmlTagName() {
        return "CompletedCellsInEAI-EUTRA";
    }

    @Override
    public Class<NGAP_CompletedCellsInEAI_EUTRA_Item> getItemType() {
        return NGAP_CompletedCellsInEAI_EUTRA_Item.class;
    }
}
