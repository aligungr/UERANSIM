package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_CompletedCellsInEAI_NR extends NGAP_SequenceOf<NGAP_CompletedCellsInEAI_NR_Item> {

    public NGAP_CompletedCellsInEAI_NR() {
        super();
    }

    public NGAP_CompletedCellsInEAI_NR(List<NGAP_CompletedCellsInEAI_NR_Item> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "CompletedCellsInEAI-NR";
    }

    @Override
    public String getXmlTagName() {
        return "CompletedCellsInEAI-NR";
    }

    @Override
    public Class<NGAP_CompletedCellsInEAI_NR_Item> getItemType() {
        return NGAP_CompletedCellsInEAI_NR_Item.class;
    }
}
