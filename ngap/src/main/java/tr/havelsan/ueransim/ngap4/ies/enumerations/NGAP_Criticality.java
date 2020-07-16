package tr.havelsan.ueransim.ngap4.ies.enumerations;

import tr.havelsan.ueransim.ngap4.core.NgapEnumerated;

public class NGAP_Criticality extends NgapEnumerated {

    public static final NGAP_Criticality REJECT = new NGAP_Criticality("reject");
    public static final NGAP_Criticality IGNORE = new NGAP_Criticality("ignore");
    public static final NGAP_Criticality NOTIFY = new NGAP_Criticality("notify");

    protected NGAP_Criticality(String sValue) {
        super(sValue);
    }

    @Override
    protected String getAsnName() {
        return "Criticality";
    }

    @Override
    protected String getXmlTagName() {
        return "Criticality";
    }
}
