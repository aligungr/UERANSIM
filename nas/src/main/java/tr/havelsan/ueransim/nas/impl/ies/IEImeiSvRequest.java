/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.core.ProtocolEnum;
import tr.havelsan.ueransim.nas.core.ies.InformationElement1;
import tr.havelsan.ueransim.utils.bits.Bit4;

public class IEImeiSvRequest extends InformationElement1 {
    public EImeiSvRequest imeiSvRequest;

    public IEImeiSvRequest() {
    }

    public IEImeiSvRequest(EImeiSvRequest imeiSvRequest) {
        this.imeiSvRequest = imeiSvRequest;
    }

    @Override
    public IEImeiSvRequest decodeIE1(Bit4 value) {
        var res = new IEImeiSvRequest();
        res.imeiSvRequest = EImeiSvRequest.fromValue(value.intValue() & 0b111);
        return res;
    }

    @Override
    public int encodeIE1() {
        return imeiSvRequest.intValue();
    }

    public static class EImeiSvRequest extends ProtocolEnum {
        public static final EImeiSvRequest NOT_REQUESTED
                = new EImeiSvRequest(0b000, "IMEISV not requested");
        public static final EImeiSvRequest REQUESTED
                = new EImeiSvRequest(0b001, "IMEISV requested");

        private EImeiSvRequest(int value, String name) {
            super(value, name);
        }

        public static EImeiSvRequest fromValue(int value) {
            return fromValueGeneric(EImeiSvRequest.class, value, null);
        }
    }
}
