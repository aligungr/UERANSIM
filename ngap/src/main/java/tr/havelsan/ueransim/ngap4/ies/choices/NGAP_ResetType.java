package tr.havelsan.ueransim.ngap4.ies.choices;

import tr.havelsan.ueransim.ngap4.core.*;
import tr.havelsan.ueransim.utils.bits.*;
import tr.havelsan.ueransim.utils.octets.*;
import tr.havelsan.ueransim.ngap4.ies.bit_strings.*;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.*;
import tr.havelsan.ueransim.ngap4.ies.printable_strings.*;
import tr.havelsan.ueransim.ngap4.ies.sequences.*;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.*;
import tr.havelsan.ueransim.ngap4.ies.choices.*;
import tr.havelsan.ueransim.ngap4.ies.integers.*;
import tr.havelsan.ueransim.ngap4.ies.enumerations.*;

import java.util.List;

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
