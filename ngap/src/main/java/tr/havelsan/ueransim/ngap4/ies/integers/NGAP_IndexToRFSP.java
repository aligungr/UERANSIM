package tr.havelsan.ueransim.ngap4.ies.integers;

import tr.havelsan.ueransim.ngap4.core.NgapInteger;

public class NGAP_IndexToRFSP extends NgapInteger {

    @Override
    protected String getAsnName() {
        return "IndexToRFSP";
    }

    @Override
    protected String getXmlTagName() {
        return "IndexToRFSP";
    }
}
