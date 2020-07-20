package tr.havelsan.ueransim.ngap0.ies.enumerations;

import tr.havelsan.ueransim.ngap0.core.*;

public class NGAP_PagingOrigin extends NGAP_Enumerated {

    public static final NGAP_PagingOrigin NON_3GPP = new NGAP_PagingOrigin("non-3gpp");

    protected NGAP_PagingOrigin(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "PagingOrigin";
    }

    @Override
    public String getXmlTagName() {
        return "PagingOrigin";
    }
}
