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

public class NGAP_TNLMappingList extends NgapSequenceOf<NGAP_TNLMappingItem> {

    public NGAP_TNLMappingList() {
        super();
    }

    public NGAP_TNLMappingList(List<NGAP_TNLMappingItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "TNLMappingList";
    }

    @Override
    public String getXmlTagName() {
        return "TNLMappingList";
    }

    @Override
    public Class<NGAP_TNLMappingItem> getItemType() {
        return NGAP_TNLMappingItem.class;
    }
}
