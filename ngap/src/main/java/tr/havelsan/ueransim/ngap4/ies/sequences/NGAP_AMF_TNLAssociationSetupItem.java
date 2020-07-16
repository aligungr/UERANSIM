package tr.havelsan.ueransim.ngap4.ies.sequences;

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

public class NGAP_AMF_TNLAssociationSetupItem extends NgapSequence {

    public NGAP_CPTransportLayerInformation aMF_TNLAssociationAddress;

    @Override
    protected String getAsnName() {
        return "AMF-TNLAssociationSetupItem";
    }

    @Override
    protected String getXmlTagName() {
        return "AMF-TNLAssociationSetupItem";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"aMF-TNLAssociationAddress"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"aMF_TNLAssociationAddress"};
    }
}
