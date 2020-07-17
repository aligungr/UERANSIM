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

public class NGAP_QosFlowInformationList extends NgapSequenceOf<NGAP_QosFlowInformationItem> {

    public NGAP_QosFlowInformationList() {
        super();
    }

    public NGAP_QosFlowInformationList(List<NGAP_QosFlowInformationItem> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "QosFlowInformationList";
    }

    @Override
    public String getXmlTagName() {
        return "QosFlowInformationList";
    }

    @Override
    public Class<NGAP_QosFlowInformationItem> getItemType() {
        return NGAP_QosFlowInformationItem.class;
    }
}
