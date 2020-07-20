package tr.havelsan.ueransim.ngap0.ies.enumerations;

import tr.havelsan.ueransim.ngap0.core.*;

public class NGAP_AdditionalQosFlowInformation extends NGAP_Enumerated {

    public static final NGAP_AdditionalQosFlowInformation MORE_LIKELY = new NGAP_AdditionalQosFlowInformation("more-likely");

    protected NGAP_AdditionalQosFlowInformation(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "AdditionalQosFlowInformation";
    }

    @Override
    public String getXmlTagName() {
        return "AdditionalQosFlowInformation";
    }
}
