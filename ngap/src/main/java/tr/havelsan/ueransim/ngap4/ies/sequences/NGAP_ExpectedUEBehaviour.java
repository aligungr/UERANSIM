package tr.havelsan.ueransim.ngap4.ies.sequences;

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

public class NGAP_ExpectedUEBehaviour extends NgapSequence {

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
