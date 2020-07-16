package tr.havelsan.ueransim.ngap4.ies.enumerations;

import tr.havelsan.ueransim.ngap4.core.NgapEnumerated;

public class NGAP_PagingDRX extends NgapEnumerated {

    public static final NGAP_PagingDRX V32 = new NGAP_PagingDRX("v32");
    public static final NGAP_PagingDRX V64 = new NGAP_PagingDRX("v64");
    public static final NGAP_PagingDRX V128 = new NGAP_PagingDRX("v128");
    public static final NGAP_PagingDRX V256 = new NGAP_PagingDRX("v256");

    protected NGAP_PagingDRX(String sValue) {
        super(sValue);
    }

    @Override
    protected String getAsnName() {
        return "PagingDRX";
    }

    @Override
    protected String getXmlTagName() {
        return "PagingDRX";
    }
}
