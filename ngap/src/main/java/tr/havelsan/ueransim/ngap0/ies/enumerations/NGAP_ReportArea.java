package tr.havelsan.ueransim.ngap0.ies.enumerations;

import tr.havelsan.ueransim.ngap0.core.*;

public class NGAP_ReportArea extends NGAP_Enumerated {

    public static final NGAP_ReportArea CELL = new NGAP_ReportArea("cell");

    protected NGAP_ReportArea(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "ReportArea";
    }

    @Override
    public String getXmlTagName() {
        return "ReportArea";
    }
}
