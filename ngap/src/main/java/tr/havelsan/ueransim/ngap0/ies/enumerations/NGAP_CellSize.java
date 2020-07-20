package tr.havelsan.ueransim.ngap0.ies.enumerations;

import tr.havelsan.ueransim.ngap0.core.*;

public class NGAP_CellSize extends NGAP_Enumerated {

    public static final NGAP_CellSize VERYSMALL = new NGAP_CellSize("verysmall");
    public static final NGAP_CellSize SMALL = new NGAP_CellSize("small");
    public static final NGAP_CellSize MEDIUM = new NGAP_CellSize("medium");
    public static final NGAP_CellSize LARGE = new NGAP_CellSize("large");

    protected NGAP_CellSize(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "CellSize";
    }

    @Override
    public String getXmlTagName() {
        return "CellSize";
    }
}
