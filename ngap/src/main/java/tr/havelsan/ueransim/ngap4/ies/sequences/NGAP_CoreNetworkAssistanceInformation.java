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

public class NGAP_CoreNetworkAssistanceInformation extends NgapSequence {

    public NGAP_UEIdentityIndexValue uEIdentityIndexValue;
    public NGAP_PagingDRX uESpecificDRX;
    public NGAP_PeriodicRegistrationUpdateTimer periodicRegistrationUpdateTimer;
    public NGAP_MICOModeIndication mICOModeIndication;
    public NGAP_TAIListForInactive tAIListForInactive;
    public NGAP_ExpectedUEBehaviour expectedUEBehaviour;

    @Override
    protected String getAsnName() {
        return "CoreNetworkAssistanceInformation";
    }

    @Override
    protected String getXmlTagName() {
        return "CoreNetworkAssistanceInformation";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"uEIdentityIndexValue", "uESpecificDRX", "periodicRegistrationUpdateTimer", "mICOModeIndication", "tAIListForInactive", "expectedUEBehaviour"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"uEIdentityIndexValue", "uESpecificDRX", "periodicRegistrationUpdateTimer", "mICOModeIndication", "tAIListForInactive", "expectedUEBehaviour"};
    }
}
