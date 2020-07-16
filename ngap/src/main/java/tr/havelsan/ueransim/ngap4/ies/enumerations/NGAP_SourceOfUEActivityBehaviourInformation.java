package tr.havelsan.ueransim.ngap4.ies.enumerations;

import tr.havelsan.ueransim.ngap4.core.NgapEnumerated;

public class NGAP_SourceOfUEActivityBehaviourInformation extends NgapEnumerated {

    public static final NGAP_SourceOfUEActivityBehaviourInformation SUBSCRIPTION_INFORMATION = new NGAP_SourceOfUEActivityBehaviourInformation("subscription-information");
    public static final NGAP_SourceOfUEActivityBehaviourInformation STATISTICS = new NGAP_SourceOfUEActivityBehaviourInformation("statistics");

    protected NGAP_SourceOfUEActivityBehaviourInformation(String sValue) {
        super(sValue);
    }

    @Override
    protected String getAsnName() {
        return "SourceOfUEActivityBehaviourInformation";
    }

    @Override
    protected String getXmlTagName() {
        return "SourceOfUEActivityBehaviourInformation";
    }
}
