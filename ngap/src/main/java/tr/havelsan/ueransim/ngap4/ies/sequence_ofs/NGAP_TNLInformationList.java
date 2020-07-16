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

public class NGAP_TNLInformationList extends NgapSequenceOf<NGAP_TNLInformationItem> {

    @Override
    protected String getAsnName() {
        return "TNLInformationList";
    }

    @Override
    protected String getXmlTagName() {
        return "TNLInformationList";
    }

    @Override
    public Class<NGAP_TNLInformationItem> getItemType() {
        return NGAP_TNLInformationItem.class;
    }
}
