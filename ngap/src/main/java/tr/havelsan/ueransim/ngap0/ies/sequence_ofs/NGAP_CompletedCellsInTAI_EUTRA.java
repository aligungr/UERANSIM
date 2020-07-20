package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_CompletedCellsInTAI_EUTRA extends NGAP_SequenceOf<NGAP_CompletedCellsInTAI_EUTRA_Item> {

    public NGAP_CompletedCellsInTAI_EUTRA() {
        super();
    }

    public NGAP_CompletedCellsInTAI_EUTRA(List<NGAP_CompletedCellsInTAI_EUTRA_Item> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "CompletedCellsInTAI-EUTRA";
    }

    @Override
    public String getXmlTagName() {
        return "CompletedCellsInTAI-EUTRA";
    }

    @Override
    public Class<NGAP_CompletedCellsInTAI_EUTRA_Item> getItemType() {
        return NGAP_CompletedCellsInTAI_EUTRA_Item.class;
    }
}
