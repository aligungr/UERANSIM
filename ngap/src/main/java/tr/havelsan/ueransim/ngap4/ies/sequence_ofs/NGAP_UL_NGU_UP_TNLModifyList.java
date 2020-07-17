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

public class NGAP_UL_NGU_UP_TNLModifyList extends NGAP_SequenceOf<NGAP_UL_NGU_UP_TNLModifyItem> {

    public NGAP_UL_NGU_UP_TNLModifyList() {
        super();
    }

    public NGAP_UL_NGU_UP_TNLModifyList(List<NGAP_UL_NGU_UP_TNLModifyItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "UL-NGU-UP-TNLModifyList";
    }

    @Override
    public String getXmlTagName() {
        return "UL-NGU-UP-TNLModifyList";
    }

    @Override
    public Class<NGAP_UL_NGU_UP_TNLModifyItem> getItemType() {
        return NGAP_UL_NGU_UP_TNLModifyItem.class;
    }
}
