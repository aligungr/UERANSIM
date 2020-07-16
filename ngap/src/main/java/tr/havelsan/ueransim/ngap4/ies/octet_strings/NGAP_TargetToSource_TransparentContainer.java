package tr.havelsan.ueransim.ngap4.ies.octet_strings;

import tr.havelsan.ueransim.ngap4.core.NgapOctetString;

public class NGAP_TargetToSource_TransparentContainer extends NgapOctetString {

    @Override
    protected String getAsnName() {
        return "TargetToSource-TransparentContainer";
    }

    @Override
    protected String getXmlTagName() {
        return "TargetToSource-TransparentContainer";
    }
}
