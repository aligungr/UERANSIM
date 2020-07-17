package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

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

import java.util.List;

public class NGAP_UEHistoryInformation extends NGAP_SequenceOf<NGAP_LastVisitedCellItem> {

    public NGAP_UEHistoryInformation() {
        super();
    }

    public NGAP_UEHistoryInformation(List<NGAP_LastVisitedCellItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "UEHistoryInformation";
    }

    @Override
    public String getXmlTagName() {
        return "UEHistoryInformation";
    }

    @Override
    public Class<NGAP_LastVisitedCellItem> getItemType() {
        return NGAP_LastVisitedCellItem.class;
    }
}
