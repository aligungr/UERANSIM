package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_XnExtTLAs extends NGAP_SequenceOf<NGAP_XnExtTLA_Item> {

    public NGAP_XnExtTLAs() {
        super();
    }

    public NGAP_XnExtTLAs(List<NGAP_XnExtTLA_Item> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "XnExtTLAs";
    }

    @Override
    public String getXmlTagName() {
        return "XnExtTLAs";
    }

    @Override
    public Class<NGAP_XnExtTLA_Item> getItemType() {
        return NGAP_XnExtTLA_Item.class;
    }
}
