package tr.havelsan.ueransim.ngap4.ies.enumerations;

import tr.havelsan.ueransim.ngap4.core.NgapEnumerated;

public class NGAP_PagingOrigin extends NgapEnumerated {

    public static final NGAP_PagingOrigin NON_3GPP = new NGAP_PagingOrigin("non-3gpp");

    protected NGAP_PagingOrigin(String sValue) {
        super(sValue);
    }

    @Override
    protected String getAsnName() {
        return "PagingOrigin";
    }

    @Override
    protected String getXmlTagName() {
        return "PagingOrigin";
    }
}
