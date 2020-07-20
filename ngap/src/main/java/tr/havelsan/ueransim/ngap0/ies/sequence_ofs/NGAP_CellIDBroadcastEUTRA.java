package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_CellIDBroadcastEUTRA extends NGAP_SequenceOf<NGAP_CellIDBroadcastEUTRA_Item> {

    public NGAP_CellIDBroadcastEUTRA() {
        super();
    }

    public NGAP_CellIDBroadcastEUTRA(List<NGAP_CellIDBroadcastEUTRA_Item> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "CellIDBroadcastEUTRA";
    }

    @Override
    public String getXmlTagName() {
        return "CellIDBroadcastEUTRA";
    }

    @Override
    public Class<NGAP_CellIDBroadcastEUTRA_Item> getItemType() {
        return NGAP_CellIDBroadcastEUTRA_Item.class;
    }
}
