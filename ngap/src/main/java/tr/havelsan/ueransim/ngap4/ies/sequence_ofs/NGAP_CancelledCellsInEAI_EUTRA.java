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

public class NGAP_CancelledCellsInEAI_EUTRA extends NGAP_SequenceOf<NGAP_CancelledCellsInEAI_EUTRA_Item> {

    public NGAP_CancelledCellsInEAI_EUTRA() {
        super();
    }

    public NGAP_CancelledCellsInEAI_EUTRA(List<NGAP_CancelledCellsInEAI_EUTRA_Item> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "CancelledCellsInEAI-EUTRA";
    }

    @Override
    public String getXmlTagName() {
        return "CancelledCellsInEAI-EUTRA";
    }

    @Override
    public Class<NGAP_CancelledCellsInEAI_EUTRA_Item> getItemType() {
        return NGAP_CancelledCellsInEAI_EUTRA_Item.class;
    }
}
