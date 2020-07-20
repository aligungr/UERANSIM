package tr.havelsan.ueransim.ngap0.ies.choices;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.*;
import tr.havelsan.ueransim.ngap0.ies.enumerations.*;

public class NGAP_ResetType extends NGAP_Choice {

    public NGAP_ResetAll nG_Interface;
    public NGAP_UE_associatedLogicalNG_connectionList partOfNG_Interface;

    @Override
    public String getAsnName() {
        return "ResetType";
    }

    @Override
    public String getXmlTagName() {
        return "ResetType";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"nG-Interface", "partOfNG-Interface"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"nG_Interface", "partOfNG_Interface"};
    }
}
