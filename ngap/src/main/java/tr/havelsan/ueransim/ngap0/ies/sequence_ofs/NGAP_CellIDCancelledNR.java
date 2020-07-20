package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_CellIDCancelledNR extends NGAP_SequenceOf<NGAP_CellIDCancelledNR_Item> {

    public NGAP_CellIDCancelledNR() {
        super();
    }

    public NGAP_CellIDCancelledNR(List<NGAP_CellIDCancelledNR_Item> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "CellIDCancelledNR";
    }

    @Override
    public String getXmlTagName() {
        return "CellIDCancelledNR";
    }

    @Override
    public Class<NGAP_CellIDCancelledNR_Item> getItemType() {
        return NGAP_CellIDCancelledNR_Item.class;
    }
}
