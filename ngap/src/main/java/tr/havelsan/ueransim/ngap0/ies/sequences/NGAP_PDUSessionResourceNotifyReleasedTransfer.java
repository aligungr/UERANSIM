package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.choices.*;

public class NGAP_PDUSessionResourceNotifyReleasedTransfer extends NGAP_Sequence {

    public NGAP_Cause cause;

    @Override
    public String getAsnName() {
        return "PDUSessionResourceNotifyReleasedTransfer";
    }

    @Override
    public String getXmlTagName() {
        return "PDUSessionResourceNotifyReleasedTransfer";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"cause"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"cause"};
    }
}
