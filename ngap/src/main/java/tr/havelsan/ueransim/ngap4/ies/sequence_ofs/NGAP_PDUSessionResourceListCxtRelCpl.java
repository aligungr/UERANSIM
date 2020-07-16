package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_PDUSessionResourceItemCxtRelCpl;

public class NGAP_PDUSessionResourceListCxtRelCpl extends NgapSequenceOf<NGAP_PDUSessionResourceItemCxtRelCpl> {

    @Override
    protected String getAsnName() {
        return "PDUSessionResourceListCxtRelCpl";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceListCxtRelCpl";
    }

    @Override
    public Class<NGAP_PDUSessionResourceItemCxtRelCpl> getItemType() {
        return NGAP_PDUSessionResourceItemCxtRelCpl.class;
    }
}
