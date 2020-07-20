package tr.havelsan.ueransim.ngap0.ies.enumerations;

import tr.havelsan.ueransim.ngap0.core.*;

public class NGAP_PagingDRX extends NGAP_Enumerated {

    public static final NGAP_PagingDRX V32 = new NGAP_PagingDRX("v32");
    public static final NGAP_PagingDRX V64 = new NGAP_PagingDRX("v64");
    public static final NGAP_PagingDRX V128 = new NGAP_PagingDRX("v128");
    public static final NGAP_PagingDRX V256 = new NGAP_PagingDRX("v256");

    protected NGAP_PagingDRX(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "PagingDRX";
    }

    @Override
    public String getXmlTagName() {
        return "PagingDRX";
    }
}
