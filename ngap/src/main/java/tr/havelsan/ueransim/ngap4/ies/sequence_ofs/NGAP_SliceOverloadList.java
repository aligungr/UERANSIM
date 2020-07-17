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

public class NGAP_SliceOverloadList extends NGAP_SequenceOf<NGAP_SliceOverloadItem> {

    public NGAP_SliceOverloadList() {
        super();
    }

    public NGAP_SliceOverloadList(List<NGAP_SliceOverloadItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "SliceOverloadList";
    }

    @Override
    public String getXmlTagName() {
        return "SliceOverloadList";
    }

    @Override
    public Class<NGAP_SliceOverloadItem> getItemType() {
        return NGAP_SliceOverloadItem.class;
    }
}
