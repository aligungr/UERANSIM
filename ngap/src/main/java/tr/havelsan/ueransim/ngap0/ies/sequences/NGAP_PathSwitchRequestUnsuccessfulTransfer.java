package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.choices.*;

public class NGAP_PathSwitchRequestUnsuccessfulTransfer extends NGAP_Sequence {

    public NGAP_Cause cause;

    @Override
    public String getAsnName() {
        return "PathSwitchRequestUnsuccessfulTransfer";
    }

    @Override
    public String getXmlTagName() {
        return "PathSwitchRequestUnsuccessfulTransfer";
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
