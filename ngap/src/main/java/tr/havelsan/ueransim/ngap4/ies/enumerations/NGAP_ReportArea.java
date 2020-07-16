package tr.havelsan.ueransim.ngap4.ies.enumerations;

import tr.havelsan.ueransim.ngap4.core.NgapEnumerated;

public class NGAP_ReportArea extends NgapEnumerated {

    public static final NGAP_ReportArea CELL = new NGAP_ReportArea("cell");

    protected NGAP_ReportArea(String sValue) {
        super(sValue);
    }

    @Override
    protected String getAsnName() {
        return "ReportArea";
    }

    @Override
    protected String getXmlTagName() {
        return "ReportArea";
    }
}
