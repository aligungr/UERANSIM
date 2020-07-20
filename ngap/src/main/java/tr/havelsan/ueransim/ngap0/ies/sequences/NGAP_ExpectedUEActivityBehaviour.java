package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;
import tr.havelsan.ueransim.ngap0.ies.enumerations.*;

public class NGAP_ExpectedUEActivityBehaviour extends NGAP_Sequence {

    public NGAP_ExpectedActivityPeriod expectedActivityPeriod;
    public NGAP_ExpectedIdlePeriod expectedIdlePeriod;
    public NGAP_SourceOfUEActivityBehaviourInformation sourceOfUEActivityBehaviourInformation;

    @Override
    public String getAsnName() {
        return "ExpectedUEActivityBehaviour";
    }

    @Override
    public String getXmlTagName() {
        return "ExpectedUEActivityBehaviour";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"expectedActivityPeriod", "expectedIdlePeriod", "sourceOfUEActivityBehaviourInformation"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"expectedActivityPeriod", "expectedIdlePeriod", "sourceOfUEActivityBehaviourInformation"};
    }
}
