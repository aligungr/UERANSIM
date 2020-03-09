package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.core.exceptions.NotImplementedException;
import tr.havelsan.ueransim.nas.core.ies.InformationElement6;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;

public class IEExtendedEmergencyNumberList extends InformationElement6 {

    @Override
    protected InformationElement6 decodeIE6(OctetInputStream stream, int length) {
        throw new NotImplementedException("This information element is in FFS (For Further Study) state for now (15.2.0");
    }

    @Override
    public void encodeIE6(OctetOutputStream stream) {
        throw new NotImplementedException("This information element is in FFS (For Further Study) state for now (15.2.0");
    }
}
