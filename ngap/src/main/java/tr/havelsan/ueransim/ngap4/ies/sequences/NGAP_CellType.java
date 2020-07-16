package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.enumerations.NGAP_CellSize;

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
