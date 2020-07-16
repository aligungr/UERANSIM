package tr.havelsan.ueransim.ngap4.ies.enumerations;

import tr.havelsan.ueransim.ngap4.core.NgapEnumerated;

public class NGAP_AdditionalQosFlowInformation extends NgapEnumerated {

    public static final NGAP_AdditionalQosFlowInformation MORE_LIKELY = new NGAP_AdditionalQosFlowInformation("more-likely");

    protected NGAP_AdditionalQosFlowInformation(String sValue) {
        super(sValue);
    }

    @Override
    protected String getAsnName() {
        return "AdditionalQosFlowInformation";
    }

    @Override
    protected String getXmlTagName() {
        return "AdditionalQosFlowInformation";
    }
}
