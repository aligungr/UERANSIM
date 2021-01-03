/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.core.NasValue;
import tr.havelsan.ueransim.nas.core.ProtocolEnum;
import tr.havelsan.ueransim.nas.core.ies.InformationElement6;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.OctetString;

import java.util.Arrays;

public class IEMappedEpsBearerContexts extends InformationElement6 {
    public VMappedEpsBearerContext[] mappedEpsBearerContexts;

    public IEMappedEpsBearerContexts() {
    }

    public IEMappedEpsBearerContexts(VMappedEpsBearerContext[] mappedEpsBearerContexts) {
        this.mappedEpsBearerContexts = mappedEpsBearerContexts;
    }

    @Override
    protected IEMappedEpsBearerContexts decodeIE6(OctetInputStream stream, int length) {
        var res = new IEMappedEpsBearerContexts();
        res.mappedEpsBearerContexts = Utils.decodeList(stream, new VMappedEpsBearerContext()::decode, length, VMappedEpsBearerContext.class);
        return res;
    }

    @Override
    public void encodeIE6(OctetOutputStream stream) {
        Arrays.stream(mappedEpsBearerContexts).forEach(ctx -> ctx.encode(stream));
    }

    public static class VMappedEpsBearerContext extends NasValue {
        public EEpsBearerIdentity epsBearerIdentity;
        public EEbit ebit;
        public EOperationCode operationCode;
        public VEpsParameter[] epsParameterList;

        public VMappedEpsBearerContext() {
        }

        public VMappedEpsBearerContext(EEpsBearerIdentity epsBearerIdentity, EEbit ebit, EOperationCode operationCode, VEpsParameter[] epsParameterList) {
            this.epsBearerIdentity = epsBearerIdentity;
            this.ebit = ebit;
            this.operationCode = operationCode;
            this.epsParameterList = epsParameterList;
        }

        @Override
        public VMappedEpsBearerContext decode(OctetInputStream stream) {
            var res = new VMappedEpsBearerContext();
            res.epsBearerIdentity = EEpsBearerIdentity.fromValue(stream.readOctetI() >> 4 & 0xF);

            int totalLen = stream.readOctetI();
            var flags = stream.readOctet();
            int paramCount = flags.getBitRangeI(0, 3);
            res.ebit = EEbit.fromValue(flags.getBitI(4));
            res.operationCode = EOperationCode.fromValue(flags.getBitRangeI(6, 7));
            res.epsParameterList = Utils.decodeList(stream, new VEpsParameter()::decode, totalLen - 1, VEpsParameter.class);

            return res;
        }

        @Override
        public void encode(OctetOutputStream stream) {
            var innerStream = new OctetOutputStream();
            Arrays.stream(epsParameterList).forEach(param -> param.encode(innerStream));

            stream.writeOctet(epsBearerIdentity.intValue() << 4);
            stream.writeOctet(innerStream.length() + 1);

            var flags = new Octet();
            flags = flags.setBitRange(6, 7, operationCode.intValue());
            flags = flags.setBit(4, ebit.intValue());
            flags = flags.setBitRange(0, 3, epsParameterList.length);
            stream.writeOctet(flags);

            stream.writeStream(innerStream);
        }
    }

    public static class VEpsParameter extends NasValue {
        public Octet epsParameterIdentifier;
        public OctetString content;

        public VEpsParameter() {
        }

        public VEpsParameter(Octet epsParameterIdentifier, OctetString content) {
            this.epsParameterIdentifier = epsParameterIdentifier;
            this.content = content;
        }

        @Override
        public VEpsParameter decode(OctetInputStream stream) {
            var res = new VEpsParameter();
            res.epsParameterIdentifier = stream.readOctet();
            int length = stream.readOctetI();
            res.content = stream.readOctetString(length);
            return res;
        }

        @Override
        public void encode(OctetOutputStream stream) {
            stream.writeOctet(epsParameterIdentifier);
            stream.writeOctet(content.length);
            stream.writeOctetString(content);
        }
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
            return fromValueGeneric(EEpsBearerIdentity.class, value, null);
        }
    }

    public static class EOperationCode extends ProtocolEnum {
        //public static final EOperationCode RESERVED
        //        = new EOperationCode(0b00, "Reserved");
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
            return fromValueGeneric(EOperationCode.class, value, null);
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
            return fromValueGeneric(EEbit.class, value, null);
        }
    }
}
