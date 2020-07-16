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

public class NGAP_QosFlowSetupRequestList extends NgapSequenceOf<NGAP_QosFlowSetupRequestItem> {

    @Override
    protected String getAsnName() {
        return "QosFlowSetupRequestList";
    }

    @Override
    protected String getXmlTagName() {
        return "QosFlowSetupRequestList";
    }

    @Override
    public Class<NGAP_QosFlowSetupRequestItem> getItemType() {
        return NGAP_QosFlowSetupRequestItem.class;
    }
}
