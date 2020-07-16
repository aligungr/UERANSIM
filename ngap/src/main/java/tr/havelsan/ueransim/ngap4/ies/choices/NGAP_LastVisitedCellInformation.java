package tr.havelsan.ueransim.ngap4.ies.choices;

import tr.havelsan.ueransim.ngap4.core.NgapChoice;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.NGAP_LastVisitedEUTRANCellInformation;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.NGAP_LastVisitedGERANCellInformation;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.NGAP_LastVisitedUTRANCellInformation;
import tr.havelsan.ueransim.ngap4.ies.sequences.NGAP_LastVisitedNGRANCellInformation;

public class NGAP_LastVisitedCellInformation extends NgapChoice {

    public NGAP_LastVisitedNGRANCellInformation nGRANCell;
    public NGAP_LastVisitedEUTRANCellInformation eUTRANCell;
    public NGAP_LastVisitedUTRANCellInformation uTRANCell;
    public NGAP_LastVisitedGERANCellInformation gERANCell;

    @Override
    protected String getAsnName() {
        return "LastVisitedCellInformation";
    }

    @Override
    protected String getXmlTagName() {
        return "LastVisitedCellInformation";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"nGRANCell", "eUTRANCell", "uTRANCell", "gERANCell"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"nGRANCell", "eUTRANCell", "uTRANCell", "gERANCell"};
    }
}
