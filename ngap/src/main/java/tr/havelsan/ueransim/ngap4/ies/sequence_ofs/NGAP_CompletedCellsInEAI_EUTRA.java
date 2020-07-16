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

public class NGAP_CompletedCellsInEAI_EUTRA extends NgapSequenceOf<NGAP_CompletedCellsInEAI_EUTRA_Item> {

    @Override
    protected String getAsnName() {
        return "CompletedCellsInEAI-EUTRA";
    }

    @Override
    protected String getXmlTagName() {
        return "CompletedCellsInEAI-EUTRA";
    }

    @Override
    public Class<NGAP_CompletedCellsInEAI_EUTRA_Item> getItemType() {
        return NGAP_CompletedCellsInEAI_EUTRA_Item.class;
    }
}
