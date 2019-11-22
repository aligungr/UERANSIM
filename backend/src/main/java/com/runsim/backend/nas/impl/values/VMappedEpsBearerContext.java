package com.runsim.backend.nas.impl.values;

import com.runsim.backend.nas.core.NasValue;
import com.runsim.backend.nas.core.ProtocolEnum;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.OctetOutputStream;
import com.runsim.backend.utils.Utils;
import com.runsim.backend.utils.octets.Octet;

import java.util.List;

// TODO: Bu yanlış olöuş gibi tekrar bakılsın
public class VMappedEpsBearerContext extends NasValue {
    public EEpsBearerIdentity epsBearerIdentity;
    public EOperationCode operationCode;
    public EEbit ebit;
    public List<VEpsParameter> epsParameterList;

    public static VMappedEpsBearerContext decode(OctetInputStream stream) {
        var res = new VMappedEpsBearerContext();
        res.epsBearerIdentity = EEpsBearerIdentity.fromValue(stream.readOctetI());

        int totalLen = stream.readOctetI();
        var flags = stream.readOctet();
        int paramCount = flags.getBitRangeI(0, 3);
        res.ebit = EEbit.fromValue(flags.getBitI(4));
        res.operationCode = EOperationCode.fromValue(flags.getBitRangeI(6, 7));
        res.epsParameterList = Utils.decodeList(stream, VEpsParameter::decode, 1, totalLen);

        return res;
    }

    @Override
    public void encode(OctetOutputStream stream) {
        stream.writeOctet(epsBearerIdentity.intValue());

        var octet = new Octet();
        octet = octet.setBitRange(6, 7, operationCode.intValue());
        octet = octet.setBit(4, ebit.intValue());
        octet = octet.setBitRange(0, 3, epsParameterList.size());
        stream.writeOctet(octet);

        epsParameterList.forEach(param -> param.encode(stream));
    }

    public static class EEpsBearerIdentity extends ProtocolEnum {
        public static final EEpsBearerIdentity NO_VALUE
                = new EEpsBearerIdentity(0b0000, "No EPS bearer identity assigned");
        public static final EEpsBearerIdentity VAL_1
                = new EEpsBearerIdentity(0b0001, "EPS bearer identity value 1");
        public static final EEpsBearerIdentity VAL_2
                = new EEpsBearerIdentity(0b0010, "EPS bearer identity value 2");
        public static final EEpsBearerIdentity VAL_3
                = new EEpsBearerIdentity(0b0011, "EPS bearer identity value 3");
        public static final EEpsBearerIdentity VAL_4
                = new EEpsBearerIdentity(0b0100, "EPS bearer identity value 4");
        public static final EEpsBearerIdentity VAL_5
                = new EEpsBearerIdentity(0b0101, "EPS bearer identity value 5");
        public static final EEpsBearerIdentity VAL_6
                = new EEpsBearerIdentity(0b0110, "EPS bearer identity value 6");
        public static final EEpsBearerIdentity VAL_7
                = new EEpsBearerIdentity(0b0111, "EPS bearer identity value 7");
        public static final EEpsBearerIdentity VAL_8
                = new EEpsBearerIdentity(0b1000, "EPS bearer identity value 8");
        public static final EEpsBearerIdentity VAL_9
                = new EEpsBearerIdentity(0b1001, "EPS bearer identity value 9");
        public static final EEpsBearerIdentity VAL_10
                = new EEpsBearerIdentity(0b1010, "EPS bearer identity value 10");
        public static final EEpsBearerIdentity VAL_11
                = new EEpsBearerIdentity(0b1011, "EPS bearer identity value 11");
        public static final EEpsBearerIdentity VAL_12
                = new EEpsBearerIdentity(0b1100, "EPS bearer identity value 12");
        public static final EEpsBearerIdentity VAL_13
                = new EEpsBearerIdentity(0b1101, "EPS bearer identity value 13");
        public static final EEpsBearerIdentity VAL_14
                = new EEpsBearerIdentity(0b1110, "EPS bearer identity value 14");
        public static final EEpsBearerIdentity VAL_15
                = new EEpsBearerIdentity(0b1111, "EPS bearer identity value 15");

        private EEpsBearerIdentity(int value, String name) {
            super(value, name);
        }

        public static EEpsBearerIdentity fromValue(int value) {
            return fromValueGeneric(EEpsBearerIdentity.class, value);
        }
    }

    public static class EOperationCode extends ProtocolEnum {
        public static final EOperationCode RESERVED
                = new EOperationCode(0b00, "Reserved");
        public static final EOperationCode CREATE_NEW_EPS_BEARER
                = new EOperationCode(0b01, "Create new EPS bearer");
        public static final EOperationCode DELETE_EXISTING_EPS_BEARER
                = new EOperationCode(0b10, "Delete existing EPS bearer");
        public static final EOperationCode MODIFY_EXISTING_EPS_BEARER
                = new EOperationCode(0b11, "Modify existing EPS bearer");

        private EOperationCode(int value, String name) {
            super(value, name);
        }

        public static EOperationCode fromValue(int value) {
            return fromValueGeneric(EOperationCode.class, value);
        }
    }

    public static class EEbit extends ProtocolEnum {
        public static final EEbit NOT_INCLUDED_OR_EXTENSION
                = new EEbit(0b0, "parameter list is not included / previously provided parameter list extension");
        public static final EEbit INCLUDED_OR_REPLACEMENT =
                new EEbit(0b1, "parameter list is included / previously provided parameter list replacement");

        private EEbit(int value, String name) {
            super(value, name);
        }

        public static EEbit fromValue(int value) {
            return fromValueGeneric(EEbit.class, value);
        }
    }
}
