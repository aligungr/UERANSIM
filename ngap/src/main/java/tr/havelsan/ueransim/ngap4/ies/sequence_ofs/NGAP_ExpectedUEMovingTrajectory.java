package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.*;
import tr.havelsan.ueransim.utils.bits.*;
import tr.havelsan.ueransim.utils.octets.*;
import tr.havelsan.ueransim.ngap4.ies.bit_strings.*;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.*;
import tr.havelsan.ueransim.ngap4.ies.printable_strings.*;
import tr.havelsan.ueransim.ngap4.ies.sequences.*;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.*;
import tr.havelsan.ueransim.ngap4.ies.choices.*;
import tr.havelsan.ueransim.ngap4.ies.integers.*;
import tr.havelsan.ueransim.ngap4.ies.enumerations.*;

import java.util.List;

public class NGAP_ExpectedUEMovingTrajectory extends NgapSequenceOf<NGAP_ExpectedUEMovingTrajectoryItem> {

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
