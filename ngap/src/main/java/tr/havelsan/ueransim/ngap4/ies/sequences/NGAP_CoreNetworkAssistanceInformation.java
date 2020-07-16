package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.bit_strings.NGAP_PeriodicRegistrationUpdateTimer;
import tr.havelsan.ueransim.ngap4.ies.choices.NGAP_UEIdentityIndexValue;
import tr.havelsan.ueransim.ngap4.ies.enumerations.NGAP_MICOModeIndication;
import tr.havelsan.ueransim.ngap4.ies.enumerations.NGAP_PagingDRX;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.NGAP_TAIListForInactive;

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
