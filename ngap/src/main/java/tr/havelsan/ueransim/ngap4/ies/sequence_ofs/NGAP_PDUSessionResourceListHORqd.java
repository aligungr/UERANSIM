package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_PDUSessionResourceItemHORqd;

public class NGAP_PDUSessionResourceListHORqd extends NgapSequenceOf<NGAP_PDUSessionResourceItemHORqd> {

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceListHORqd";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceListHORqd";
    }

    @Override
    public Class<NGAP_PDUSessionResourceItemHORqd> getItemType() {
        return NGAP_PDUSessionResourceItemHORqd.class;
    }
}
