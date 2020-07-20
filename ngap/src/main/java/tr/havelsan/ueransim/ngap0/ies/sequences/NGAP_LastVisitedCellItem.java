package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.choices.*;

public class NGAP_LastVisitedCellItem extends NGAP_Sequence {

    public NGAP_LastVisitedCellInformation lastVisitedCellInformation;

    @Override
    public String getAsnName() {
        return "LastVisitedCellItem";
    }

    @Override
    public String getXmlTagName() {
        return "LastVisitedCellItem";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"lastVisitedCellInformation"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"lastVisitedCellInformation"};
    }
}
