package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_CompletedCellsInTAI_NR extends NGAP_SequenceOf<NGAP_CompletedCellsInTAI_NR_Item> {

    public NGAP_CompletedCellsInTAI_NR() {
        super();
    }

    public NGAP_CompletedCellsInTAI_NR(List<NGAP_CompletedCellsInTAI_NR_Item> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "CompletedCellsInTAI-NR";
    }

    @Override
    public String getXmlTagName() {
        return "CompletedCellsInTAI-NR";
    }

    @Override
    public Class<NGAP_CompletedCellsInTAI_NR_Item> getItemType() {
        return NGAP_CompletedCellsInTAI_NR_Item.class;
    }
}
