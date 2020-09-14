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

public class NGAP_CompletedCellsInEAI_NR extends NGAP_SequenceOf<NGAP_CompletedCellsInEAI_NR_Item> {

    public NGAP_CompletedCellsInEAI_NR() {
        super();
    }

    public NGAP_CompletedCellsInEAI_NR(List<NGAP_CompletedCellsInEAI_NR_Item> value) {
        super(value);
    }

    @Override
    public String getAsnName() {
        return "CompletedCellsInEAI-NR";
    }

    @Override
    public String getXmlTagName() {
        return "CompletedCellsInEAI-NR";
    }

    @Override
    public Class<NGAP_CompletedCellsInEAI_NR_Item> getItemType() {
        return NGAP_CompletedCellsInEAI_NR_Item.class;
    }
}
