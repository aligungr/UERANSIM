package tr.havelsan.ueransim.ngap4.ies.enumerations;

import tr.havelsan.ueransim.ngap4.core.NgapEnumerated;

public class NGAP_CellSize extends NgapEnumerated {

    public static final NGAP_CellSize VERYSMALL = new NGAP_CellSize("verysmall");
    public static final NGAP_CellSize SMALL = new NGAP_CellSize("small");
    public static final NGAP_CellSize MEDIUM = new NGAP_CellSize("medium");
    public static final NGAP_CellSize LARGE = new NGAP_CellSize("large");

    protected NGAP_CellSize(String sValue) {
        super(sValue);
    }

    @Override
    protected String getAsnName() {
        return "CellSize";
    }

    @Override
    protected String getXmlTagName() {
        return "CellSize";
    }
}
