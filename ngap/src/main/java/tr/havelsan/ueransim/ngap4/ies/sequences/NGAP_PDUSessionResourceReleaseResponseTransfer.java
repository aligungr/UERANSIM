package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;

public class NGAP_PDUSessionResourceReleaseResponseTransfer extends NgapSequence {


    @Override
    protected String getAsnName() {
        return "PDUSessionResourceReleaseResponseTransfer";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionResourceReleaseResponseTransfer";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{};
    }
}
