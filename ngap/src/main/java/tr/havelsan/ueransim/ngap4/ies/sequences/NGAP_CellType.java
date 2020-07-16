package tr.havelsan.ueransim.ngap4.ies.sequences;

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

public class NGAP_CellType extends NgapSequence {

    public NGAP_CellSize cellSize;

    @Override
    protected String getAsnName() {
        return "CellType";
    }

    @Override
    protected String getXmlTagName() {
        return "CellType";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"cellSize"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"cellSize"};
    }
}
