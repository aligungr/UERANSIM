package tr.havelsan.ueransim.ngap4.ies.integers;

import tr.havelsan.ueransim.ngap4.core.NgapInteger;

public class NGAP_PDUSessionID extends NgapInteger {

    @Override
    protected String getAsnName() {
        return "PDUSessionID";
    }

    @Override
    protected String getXmlTagName() {
        return "PDUSessionID";
    }
}
