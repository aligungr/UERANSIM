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
