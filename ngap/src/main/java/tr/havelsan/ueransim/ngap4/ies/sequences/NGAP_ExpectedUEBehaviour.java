package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.enumerations.NGAP_ExpectedHOInterval;
import tr.havelsan.ueransim.ngap4.ies.enumerations.NGAP_ExpectedUEMobility;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.NGAP_ExpectedUEMovingTrajectory;

public class NGAP_ExpectedUEBehaviour extends NgapSequence {

    public NGAP_ExpectedUEActivityBehaviour expectedUEActivityBehaviour;
    public NGAP_ExpectedHOInterval expectedHOInterval;
    public NGAP_ExpectedUEMobility expectedUEMobility;
    public NGAP_ExpectedUEMovingTrajectory expectedUEMovingTrajectory;

    @Override
    protected String getAsnName() {
        return "ExpectedUEBehaviour";
    }

    @Override
    protected String getXmlTagName() {
        return "ExpectedUEBehaviour";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"expectedUEActivityBehaviour", "expectedHOInterval", "expectedUEMobility", "expectedUEMovingTrajectory"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"expectedUEActivityBehaviour", "expectedHOInterval", "expectedUEMobility", "expectedUEMovingTrajectory"};
    }
}
