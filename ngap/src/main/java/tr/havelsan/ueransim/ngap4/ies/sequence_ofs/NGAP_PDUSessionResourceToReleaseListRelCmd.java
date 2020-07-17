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

public class NGAP_PDUSessionResourceToReleaseListRelCmd extends NgapSequenceOf<NGAP_PDUSessionResourceToReleaseItemRelCmd> {

    public NGAP_PDUSessionResourceToReleaseListRelCmd() {
        super();
    }

    public NGAP_PDUSessionResourceToReleaseListRelCmd(List<NGAP_PDUSessionResourceToReleaseItemRelCmd> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "PDUSessionResourceToReleaseListRelCmd";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceToReleaseListRelCmd";
    }

    @Override
    public Class<NGAP_PDUSessionResourceToReleaseItemRelCmd> getItemType() {
        return NGAP_PDUSessionResourceToReleaseItemRelCmd.class;
    }
}
