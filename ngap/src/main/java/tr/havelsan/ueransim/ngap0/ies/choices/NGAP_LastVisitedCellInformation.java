package tr.havelsan.ueransim.ngap0.ies.choices;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.pdu.*;
import tr.havelsan.ueransim.utils.bits.*;
import tr.havelsan.ueransim.utils.octets.*;
import tr.havelsan.ueransim.ngap0.ies.bit_strings.*;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.*;
import tr.havelsan.ueransim.ngap0.ies.printable_strings.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.*;
import tr.havelsan.ueransim.ngap0.ies.choices.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;
import tr.havelsan.ueransim.ngap0.ies.enumerations.*;

import java.util.List;

public class NGAP_LastVisitedCellInformation extends NGAP_Choice {

    public NGAP_LastVisitedNGRANCellInformation nGRANCell;
    public NGAP_LastVisitedEUTRANCellInformation eUTRANCell;
    public NGAP_LastVisitedUTRANCellInformation uTRANCell;
    public NGAP_LastVisitedGERANCellInformation gERANCell;

    @Override
    public String getAsnName() {
        return "LastVisitedCellInformation";
    }

    @Override
    public String getXmlTagName() {
        return "LastVisitedCellInformation";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"nGRANCell", "eUTRANCell", "uTRANCell", "gERANCell"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"nGRANCell", "eUTRANCell", "uTRANCell", "gERANCell"};
    }
}
