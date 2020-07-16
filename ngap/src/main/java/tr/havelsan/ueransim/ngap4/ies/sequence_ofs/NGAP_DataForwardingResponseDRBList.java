package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_DataForwardingResponseDRBItem;

public class NGAP_DataForwardingResponseDRBList extends NgapSequenceOf<NGAP_DataForwardingResponseDRBItem> {

    @Override
    protected String getAsnName() {
        return "DataForwardingResponseDRBList";
    }

    @Override
    protected String getXmlTagName() {
        return "DataForwardingResponseDRBList";
    }

    @Override
    public Class<NGAP_DataForwardingResponseDRBItem> getItemType() {
        return NGAP_DataForwardingResponseDRBItem.class;
    }
}
