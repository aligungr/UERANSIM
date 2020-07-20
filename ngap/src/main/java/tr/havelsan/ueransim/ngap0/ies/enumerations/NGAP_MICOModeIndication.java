package tr.havelsan.ueransim.ngap0.ies.enumerations;

import tr.havelsan.ueransim.ngap0.core.*;

public class NGAP_MICOModeIndication extends NGAP_Enumerated {

    public static final NGAP_MICOModeIndication TRUE = new NGAP_MICOModeIndication("true");

    protected NGAP_MICOModeIndication(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "MICOModeIndication";
    }

    @Override
    public String getXmlTagName() {
        return "MICOModeIndication";
    }
}
