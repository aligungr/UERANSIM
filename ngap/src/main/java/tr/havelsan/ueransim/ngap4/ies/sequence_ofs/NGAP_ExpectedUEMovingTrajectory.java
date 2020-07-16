package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_ExpectedUEMovingTrajectoryItem;

public class NGAP_ExpectedUEMovingTrajectory extends NgapSequenceOf<NGAP_ExpectedUEMovingTrajectoryItem> {

    @Override
    protected String getAsnName() {
        return "ExpectedUEMovingTrajectory";
    }

    @Override
    protected String getXmlTagName() {
        return "ExpectedUEMovingTrajectory";
    }

    @Override
    public Class<NGAP_ExpectedUEMovingTrajectoryItem> getItemType() {
        return NGAP_ExpectedUEMovingTrajectoryItem.class;
    }
}
