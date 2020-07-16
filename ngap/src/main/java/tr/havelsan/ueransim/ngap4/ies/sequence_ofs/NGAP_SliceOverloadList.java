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

public class NGAP_SliceOverloadList extends NgapSequenceOf<NGAP_SliceOverloadItem> {

    @Override
    protected String getAsnName() {
        return "SliceOverloadList";
    }

    @Override
    protected String getXmlTagName() {
        return "SliceOverloadList";
    }

    @Override
    public Class<NGAP_SliceOverloadItem> getItemType() {
        return NGAP_SliceOverloadItem.class;
    }
}
