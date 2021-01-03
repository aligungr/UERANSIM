/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.NGAP_SequenceOf;
import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_ExpectedUEMovingTrajectoryItem;

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
