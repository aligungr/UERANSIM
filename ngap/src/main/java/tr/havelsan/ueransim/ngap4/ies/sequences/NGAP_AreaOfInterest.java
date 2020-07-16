package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.NGAP_AreaOfInterestCellList;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.NGAP_AreaOfInterestRANNodeList;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.NGAP_AreaOfInterestTAIList;

public class NGAP_AreaOfInterest extends NgapSequence {

    public NGAP_AreaOfInterestTAIList areaOfInterestTAIList;
    public NGAP_AreaOfInterestCellList areaOfInterestCellList;
    public NGAP_AreaOfInterestRANNodeList areaOfInterestRANNodeList;

    @Override
    protected String getAsnName() {
        return "AreaOfInterest";
    }

    @Override
    protected String getXmlTagName() {
        return "AreaOfInterest";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"areaOfInterestTAIList", "areaOfInterestCellList", "areaOfInterestRANNodeList"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"areaOfInterestTAIList", "areaOfInterestCellList", "areaOfInterestRANNodeList"};
    }
}
