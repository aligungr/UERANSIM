package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.enumerations.NGAP_SourceOfUEActivityBehaviourInformation;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_ExpectedActivityPeriod;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_ExpectedIdlePeriod;

public class NGAP_ExpectedUEActivityBehaviour extends NgapSequence {

    public NGAP_ExpectedActivityPeriod expectedActivityPeriod;
    public NGAP_ExpectedIdlePeriod expectedIdlePeriod;
    public NGAP_SourceOfUEActivityBehaviourInformation sourceOfUEActivityBehaviourInformation;

    @Override
    protected String getAsnName() {
        return "ExpectedUEActivityBehaviour";
    }

    @Override
    protected String getXmlTagName() {
        return "ExpectedUEActivityBehaviour";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"expectedActivityPeriod", "expectedIdlePeriod", "sourceOfUEActivityBehaviourInformation"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"expectedActivityPeriod", "expectedIdlePeriod", "sourceOfUEActivityBehaviourInformation"};
    }
}
