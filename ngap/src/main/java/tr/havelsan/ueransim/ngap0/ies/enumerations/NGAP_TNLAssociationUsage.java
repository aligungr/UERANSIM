package tr.havelsan.ueransim.ngap0.ies.enumerations;

import tr.havelsan.ueransim.ngap0.core.*;

public class NGAP_TNLAssociationUsage extends NGAP_Enumerated {

    public static final NGAP_TNLAssociationUsage UE = new NGAP_TNLAssociationUsage("ue");
    public static final NGAP_TNLAssociationUsage NON_UE = new NGAP_TNLAssociationUsage("non-ue");
    public static final NGAP_TNLAssociationUsage BOTH = new NGAP_TNLAssociationUsage("both");

    protected NGAP_TNLAssociationUsage(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "TNLAssociationUsage";
    }

    @Override
    public String getXmlTagName() {
        return "TNLAssociationUsage";
    }
}
