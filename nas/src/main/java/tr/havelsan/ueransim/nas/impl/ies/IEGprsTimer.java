/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.core.ProtocolEnum;
import tr.havelsan.ueransim.nas.core.ies.InformationElement3;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.bits.Bit5;
import tr.havelsan.ueransim.utils.octets.Octet;

public class IEGprsTimer extends InformationElement3 {
    public Bit5 timerValue;
    public EGprsTimerValueUnit timerValueUnit;

    public IEGprsTimer() {
    }

    public IEGprsTimer(Bit5 timerValue, EGprsTimerValueUnit timerValueUnit) {
        this.timerValue = timerValue;
        this.timerValueUnit = timerValueUnit;
    }

    @Override
    protected IEGprsTimer decodeIE3(OctetInputStream stream) {
        var res = new IEGprsTimer();
        var octet = stream.readOctet();
        res.timerValue = new Bit5(octet.getBitRangeI(0, 4));
        res.timerValueUnit = EGprsTimerValueUnit.fromValue(octet.getBitRangeI(5, 7));
        return res;
    }

    @Override
    public void encodeIE3(OctetOutputStream stream) {
        var octet = new Octet();
        octet = octet.setBitRange(0, 4, timerValue.intValue());
        octet = octet.setBitRange(5, 5, timerValueUnit.intValue());
        stream.writeOctet(octet);
    }

    public static class EGprsTimerValueUnit extends ProtocolEnum {
        public static final EGprsTimerValueUnit MULTIPLES_OF_2_SECONDS
                = new EGprsTimerValueUnit(0b000, "value is incremented in multiples of 2 seconds");
        public static final EGprsTimerValueUnit MULTIPLES_OF_1_MINUTE
                = new EGprsTimerValueUnit(0b001, "value is incremented in multiples of 1 minute ");
        public static final EGprsTimerValueUnit MULTIPLES_OF_DECIHOURS
                = new EGprsTimerValueUnit(0b010, "value is incremented in multiples of decihours");
        public static final EGprsTimerValueUnit TIMER_IS_DEACTIVATED
                = new EGprsTimerValueUnit(0b111, "value indicates that the timer is deactivated.");

        private EGprsTimerValueUnit(int value, String name) {
            super(value, name);
        }

        public static EGprsTimerValueUnit fromValue(int value) {
            return fromValueGeneric(EGprsTimerValueUnit.class, value, null);
        }
    }
}
