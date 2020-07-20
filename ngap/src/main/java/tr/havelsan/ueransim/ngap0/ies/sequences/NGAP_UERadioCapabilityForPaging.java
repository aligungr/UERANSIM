package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.*;

public class NGAP_UERadioCapabilityForPaging extends NGAP_Sequence {

    public NGAP_UERadioCapabilityForPagingOfNR uERadioCapabilityForPagingOfNR;
    public NGAP_UERadioCapabilityForPagingOfEUTRA uERadioCapabilityForPagingOfEUTRA;

    @Override
    public String getAsnName() {
        return "UERadioCapabilityForPaging";
    }

    @Override
    public String getXmlTagName() {
        return "UERadioCapabilityForPaging";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"uERadioCapabilityForPagingOfNR", "uERadioCapabilityForPagingOfEUTRA"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"uERadioCapabilityForPagingOfNR", "uERadioCapabilityForPagingOfEUTRA"};
    }
}
