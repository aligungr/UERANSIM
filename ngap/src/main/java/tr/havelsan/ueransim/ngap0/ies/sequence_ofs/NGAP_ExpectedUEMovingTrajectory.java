package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;

import java.util.List;

public class NGAP_ExpectedUEMovingTrajectory extends NGAP_SequenceOf<NGAP_ExpectedUEMovingTrajectoryItem> {

    public NGAP_ExpectedUEMovingTrajectory() {
        super();
    }

    public NGAP_ExpectedUEMovingTrajectory(List<NGAP_ExpectedUEMovingTrajectoryItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "ExpectedUEMovingTrajectory";
    }

    @Override
    public String getXmlTagName() {
        return "ExpectedUEMovingTrajectory";
    }

    @Override
    public Class<NGAP_ExpectedUEMovingTrajectoryItem> getItemType() {
        return NGAP_ExpectedUEMovingTrajectoryItem.class;
    }
}
