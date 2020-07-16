package tr.havelsan.ueransim.ngap4.ies.enumerations;

import tr.havelsan.ueransim.ngap4.core.NgapEnumerated;

public class NGAP_TNLAssociationUsage extends NgapEnumerated {

    public static final NGAP_TNLAssociationUsage UE = new NGAP_TNLAssociationUsage("ue");
    public static final NGAP_TNLAssociationUsage NON_UE = new NGAP_TNLAssociationUsage("non-ue");
    public static final NGAP_TNLAssociationUsage BOTH = new NGAP_TNLAssociationUsage("both");

    protected NGAP_TNLAssociationUsage(String sValue) {
        super(sValue);
    }

    @Override
    protected String getAsnName() {
        return "TNLAssociationUsage";
    }

    @Override
    protected String getXmlTagName() {
        return "TNLAssociationUsage";
    }
}
