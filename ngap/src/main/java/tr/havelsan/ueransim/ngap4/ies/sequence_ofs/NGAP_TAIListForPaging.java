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

public class NGAP_TAIListForPaging extends NgapSequenceOf<NGAP_TAIListForPagingItem> {

    public NGAP_TAIListForPaging() {
        super();
    }

    public NGAP_TAIListForPaging(List<NGAP_TAIListForPagingItem> value) {
        super(value);
    }

    @Override
    protected String getAsnName() {
        return "TAIListForPaging";
    }

    @Override
    protected String getXmlTagName() {
        return "TAIListForPaging";
    }

    @Override
    public Class<NGAP_TAIListForPagingItem> getItemType() {
        return NGAP_TAIListForPagingItem.class;
    }
}
