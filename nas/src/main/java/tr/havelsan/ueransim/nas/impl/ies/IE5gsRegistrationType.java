package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.core.ProtocolEnum;
import tr.havelsan.ueransim.nas.core.ies.InformationElement1;
import tr.havelsan.ueransim.nas.impl.enums.ERegistrationType;
import tr.havelsan.ueransim.utils.bits.Bit4;

public class IE5gsRegistrationType extends InformationElement1 {
    public EFollowOnRequest followOnRequestPending;
    public ERegistrationType registrationType;

    public IE5gsRegistrationType() {
    }

    public IE5gsRegistrationType(EFollowOnRequest followOnRequestPending, ERegistrationType registrationType) {
        this.followOnRequestPending = followOnRequestPending;
        this.registrationType = registrationType;
    }

    @Override
    public InformationElement1 decodeIE1(Bit4 value) {
        int val = value.intValue();

        var res = new IE5gsRegistrationType();
        res.registrationType = ERegistrationType.fromValue(val & 0b111);
        res.followOnRequestPending = EFollowOnRequest.fromValue(val >> 3 & 0b1);
        return res;
    }

    @Override
    public int encodeIE1() {
        return followOnRequestPending.intValue() << 3 | registrationType.intValue();
    }

    public static class EFollowOnRequest extends ProtocolEnum {
        public static final EFollowOnRequest NO_FOR_PENDING
                = new EFollowOnRequest(0b0, "No follow-on request pending");
        public static final EFollowOnRequest FOR_PENDING
                = new EFollowOnRequest(0b1, "Follow-on request pending");

        private EFollowOnRequest(int value, String name) {
            super(value, name);
        }

        public static EFollowOnRequest fromValue(int value) {
            return fromValueGeneric(EFollowOnRequest.class, value, null);
        }
    }
}
