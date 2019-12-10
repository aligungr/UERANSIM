package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.exceptions.IncorrectImplementationException;
import tr.havelsan.ueransim.utils.OctetOutputStream;

public abstract class IESuciMobileIdentity extends IE5gsMobileIdentity {

    @Override
    public void encodeIE6(OctetOutputStream stream) {
        throw new IncorrectImplementationException("subtypes must override this method");
    }
}
