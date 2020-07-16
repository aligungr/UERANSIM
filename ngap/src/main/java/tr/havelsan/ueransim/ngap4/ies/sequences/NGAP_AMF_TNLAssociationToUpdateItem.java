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

public class NGAP_AMF_TNLAssociationToUpdateItem extends NgapSequence {

    public NGAP_CPTransportLayerInformation aMF_TNLAssociationAddress;
    public NGAP_TNLAssociationUsage tNLAssociationUsage;
    public NGAP_TNLAddressWeightFactor tNLAddressWeightFactor;

    @Override
    protected String getAsnName() {
        return "AMF-TNLAssociationToUpdateItem";
    }

    @Override
    protected String getXmlTagName() {
        return "AMF-TNLAssociationToUpdateItem";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"aMF-TNLAssociationAddress", "tNLAssociationUsage", "tNLAddressWeightFactor"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"aMF_TNLAssociationAddress", "tNLAssociationUsage", "tNLAddressWeightFactor"};
    }
}
