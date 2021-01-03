/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_ExpectedHOInterval;
import tr.havelsan.ueransim.ngap0.ies.enumerations.NGAP_ExpectedUEMobility;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_ExpectedUEMovingTrajectory;

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
