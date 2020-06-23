package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.core.exceptions.NotImplementedException;
import tr.havelsan.ueransim.nas.core.ies.InformationElement4;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;

public class IES1UeSecurityCapability extends InformationElement4 {
    @Override
    protected InformationElement4 decodeIE4(OctetInputStream stream, int length) {
        throw new NotImplementedException("Not implemented yet: See TS 24.501 9.11.3.48A");
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        throw new NotImplementedException("Not implemented yet: See TS 24.501 9.11.3.48A");
    }
}
