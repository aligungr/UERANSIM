package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.*;
import tr.havelsan.ueransim.ngap4.pdu.*;
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

public class NGAP_RecommendedRANNodeList extends NGAP_SequenceOf<NGAP_RecommendedRANNodeItem> {

    public NGAP_RecommendedRANNodeList() {
        super();
    }

    public NGAP_RecommendedRANNodeList(List<NGAP_RecommendedRANNodeItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "RecommendedRANNodeList";
    }

    @Override
    public String getXmlTagName() {
        return "RecommendedRANNodeList";
    }

    @Override
    public Class<NGAP_RecommendedRANNodeItem> getItemType() {
        return NGAP_RecommendedRANNodeItem.class;
    }
}
