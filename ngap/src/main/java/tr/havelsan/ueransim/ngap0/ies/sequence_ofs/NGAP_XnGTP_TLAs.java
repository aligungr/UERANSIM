package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.bit_strings.*;

import java.util.List;

public class NGAP_XnGTP_TLAs extends NGAP_SequenceOf<NGAP_TransportLayerAddress> {

    public NGAP_XnGTP_TLAs() {
        super();
    }

    public NGAP_XnGTP_TLAs(List<NGAP_TransportLayerAddress> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "XnGTP-TLAs";
    }

    @Override
    public String getXmlTagName() {
        return "XnGTP-TLAs";
    }

    @Override
    public Class<NGAP_TransportLayerAddress> getItemType() {
        return NGAP_TransportLayerAddress.class;
    }
}
