package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.*;
import tr.havelsan.ueransim.ngap0.ies.enumerations.*;

public class NGAP_ExpectedUEBehaviour extends NGAP_Sequence {

    public NGAP_ExpectedUEActivityBehaviour expectedUEActivityBehaviour;
    public NGAP_ExpectedHOInterval expectedHOInterval;
    public NGAP_ExpectedUEMobility expectedUEMobility;
    public NGAP_ExpectedUEMovingTrajectory expectedUEMovingTrajectory;

    @Override
    public String getAsnName() {
        return "ExpectedUEBehaviour";
    }

    @Override
    public String getXmlTagName() {
        return "ExpectedUEBehaviour";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"expectedUEActivityBehaviour", "expectedHOInterval", "expectedUEMobility", "expectedUEMovingTrajectory"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"expectedUEActivityBehaviour", "expectedHOInterval", "expectedUEMobility", "expectedUEMovingTrajectory"};
    }
}
