package tr.havelsan.ueransim.ngap4.ies.choices;

import tr.havelsan.ueransim.ngap4.core.NgapChoice;
import tr.havelsan.ueransim.ngap4.ies.enumerations.NGAP_ResetAll;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.NGAP_UE_associatedLogicalNG_connectionList;

public class NGAP_ResetType extends NgapChoice {

    public NGAP_ResetAll nG_Interface;
    public NGAP_UE_associatedLogicalNG_connectionList partOfNG_Interface;

    @Override
    protected String getAsnName() {
        return "ResetType";
    }

    @Override
    protected String getXmlTagName() {
        return "ResetType";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"nG-Interface", "partOfNG-Interface"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"nG_Interface", "partOfNG_Interface"};
    }
}
