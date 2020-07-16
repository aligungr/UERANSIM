package tr.havelsan.ueransim.ngap4.ies.enumerations;

import tr.havelsan.ueransim.ngap4.core.NgapEnumerated;

public class NGAP_MICOModeIndication extends NgapEnumerated {

    public static final NGAP_MICOModeIndication TRUE = new NGAP_MICOModeIndication("true");

    protected NGAP_MICOModeIndication(String sValue) {
        super(sValue);
    }

    @Override
    protected String getAsnName() {
        return "MICOModeIndication";
    }

    @Override
    protected String getXmlTagName() {
        return "MICOModeIndication";
    }
}
