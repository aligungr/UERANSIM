package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.NGAP_UERadioCapabilityForPagingOfEUTRA;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.NGAP_UERadioCapabilityForPagingOfNR;

public class NGAP_UERadioCapabilityForPaging extends NgapSequence {

    public NGAP_UERadioCapabilityForPagingOfNR uERadioCapabilityForPagingOfNR;
    public NGAP_UERadioCapabilityForPagingOfEUTRA uERadioCapabilityForPagingOfEUTRA;

    @Override
    protected String getAsnName() {
        return "UERadioCapabilityForPaging";
    }

    @Override
    protected String getXmlTagName() {
        return "UERadioCapabilityForPaging";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"uERadioCapabilityForPagingOfNR", "uERadioCapabilityForPagingOfEUTRA"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"uERadioCapabilityForPagingOfNR", "uERadioCapabilityForPagingOfEUTRA"};
    }
}
