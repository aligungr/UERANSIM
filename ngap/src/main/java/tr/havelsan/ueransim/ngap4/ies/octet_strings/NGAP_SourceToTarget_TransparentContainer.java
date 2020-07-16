package tr.havelsan.ueransim.ngap4.ies.octet_strings;

import tr.havelsan.ueransim.ngap4.core.NgapOctetString;

public class NGAP_SourceToTarget_TransparentContainer extends NgapOctetString {

    @Override
    protected String getAsnName() {
        return "SourceToTarget-TransparentContainer";
    }

    @Override
    protected String getXmlTagName() {
        return "SourceToTarget-TransparentContainer";
    }
}
