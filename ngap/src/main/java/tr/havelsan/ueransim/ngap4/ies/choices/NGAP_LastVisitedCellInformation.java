package tr.havelsan.ueransim.ngap4.ies.choices;

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
