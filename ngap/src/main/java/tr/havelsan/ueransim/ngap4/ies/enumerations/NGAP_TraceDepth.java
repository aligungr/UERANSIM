package tr.havelsan.ueransim.ngap4.ies.enumerations;

import tr.havelsan.ueransim.ngap4.core.NgapEnumerated;

public class NGAP_TraceDepth extends NgapEnumerated {

    public static final NGAP_TraceDepth MINIMUM = new NGAP_TraceDepth("minimum");
    public static final NGAP_TraceDepth MEDIUM = new NGAP_TraceDepth("medium");
    public static final NGAP_TraceDepth MAXIMUM = new NGAP_TraceDepth("maximum");
    public static final NGAP_TraceDepth MINIMUMWITHOUTVENDORSPECIFICEXTENSION = new NGAP_TraceDepth("minimumWithoutVendorSpecificExtension");
    public static final NGAP_TraceDepth MEDIUMWITHOUTVENDORSPECIFICEXTENSION = new NGAP_TraceDepth("mediumWithoutVendorSpecificExtension");
    public static final NGAP_TraceDepth MAXIMUMWITHOUTVENDORSPECIFICEXTENSION = new NGAP_TraceDepth("maximumWithoutVendorSpecificExtension");

    protected NGAP_TraceDepth(String sValue) {
        super(sValue);
    }

    @Override
    protected String getAsnName() {
        return "TraceDepth";
    }

    @Override
    protected String getXmlTagName() {
        return "TraceDepth";
    }
}
