package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.choices.NGAP_LastVisitedCellInformation;

public class NGAP_LastVisitedCellItem extends NgapSequence {

    public NGAP_LastVisitedCellInformation lastVisitedCellInformation;

    @Override
    protected String getAsnName() {
        return "LastVisitedCellItem";
    }

    @Override
    protected String getXmlTagName() {
        return "LastVisitedCellItem";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"lastVisitedCellInformation"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"lastVisitedCellInformation"};
    }
}
