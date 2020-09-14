package tr.havelsan.ueransim.ngap0.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.pdu.*;
import tr.havelsan.ueransim.utils.bits.*;
import tr.havelsan.ueransim.utils.octets.*;
import tr.havelsan.ueransim.ngap0.ies.bit_strings.*;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.*;
import tr.havelsan.ueransim.ngap0.ies.printable_strings.*;
import tr.havelsan.ueransim.ngap0.ies.sequences.*;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.*;
import tr.havelsan.ueransim.ngap0.ies.choices.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;
import tr.havelsan.ueransim.ngap0.ies.enumerations.*;

import java.util.List;

public class NGAP_CancelledCellsInTAI_EUTRA extends NGAP_SequenceOf<NGAP_CancelledCellsInTAI_EUTRA_Item> {

    public NGAP_CancelledCellsInTAI_EUTRA() {
        super();
    }

    public NGAP_CancelledCellsInTAI_EUTRA(List<NGAP_CancelledCellsInTAI_EUTRA_Item> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "CancelledCellsInTAI-EUTRA";
    }

    @Override
    public String getXmlTagName() {
        return "CancelledCellsInTAI-EUTRA";
    }

    @Override
    public Class<NGAP_CancelledCellsInTAI_EUTRA_Item> getItemType() {
        return NGAP_CancelledCellsInTAI_EUTRA_Item.class;
    }
}
