package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.enumerations.*;

public class NGAP_CellType extends NGAP_Sequence {

    public NGAP_CellSize cellSize;

    @Override
    public String getAsnName() {
        return "CellType";
    }

    @Override
    public String getXmlTagName() {
        return "CellType";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"cellSize"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"cellSize"};
    }
}
