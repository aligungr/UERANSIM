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

public class NGAP_QosFlowSetupResponseListSURes extends NGAP_SequenceOf<NGAP_QosFlowSetupResponseItemSURes> {

    public NGAP_QosFlowSetupResponseListSURes() {
        super();
    }

    public NGAP_QosFlowSetupResponseListSURes(List<NGAP_QosFlowSetupResponseItemSURes> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "QosFlowSetupResponseListSURes";
    }

    @Override
    public String getXmlTagName() {
        return "QosFlowSetupResponseListSURes";
    }

    @Override
    public Class<NGAP_QosFlowSetupResponseItemSURes> getItemType() {
        return NGAP_QosFlowSetupResponseItemSURes.class;
    }
}
