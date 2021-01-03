/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_AreaOfInterestCellList;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_AreaOfInterestRANNodeList;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_AreaOfInterestTAIList;

public class NGAP_AreaOfInterest extends NGAP_Sequence {

    public NGAP_AreaOfInterestTAIList areaOfInterestTAIList;
    public NGAP_AreaOfInterestCellList areaOfInterestCellList;
    public NGAP_AreaOfInterestRANNodeList areaOfInterestRANNodeList;

    @Override
    public String getAsnName() {
        return "AreaOfInterest";
    }

    @Override
    public String getXmlTagName() {
        return "AreaOfInterest";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"areaOfInterestTAIList", "areaOfInterestCellList", "areaOfInterestRANNodeList"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"areaOfInterestTAIList", "areaOfInterestCellList", "areaOfInterestRANNodeList"};
    }
}
