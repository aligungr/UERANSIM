/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.impl.values;

import tr.havelsan.ueransim.nas.core.NasValue;
import tr.havelsan.ueransim.nas.core.ProtocolEnum;
import tr.havelsan.ueransim.nas.impl.enums.EQoSFlowIdentifier;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.bits.Bit;
import tr.havelsan.ueransim.utils.bits.Bit6;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.OctetString;

import java.util.Arrays;

public class VQoSFlowDescription extends NasValue {

    public EQoSFlowIdentifier qfi;
    public EOperationCode operationCode;
    public Bit6 numberOfParameters;
    public Bit eBit;
    public VParameter[] parametersList;

    @Override
    public void encode(OctetOutputStream stream) {
        stream.writeOctet(qfi.intValue() & 0b111111);
        stream.writeOctet((operationCode.intValue() & 0b111) << 5);
        stream.writeOctet((eBit.intValue() << 6) | (numberOfParameters.intValue() & 0b111111));
        if (parametersList != null) {
            Arrays.stream(parametersList).forEach(item -> item.encode(stream));
        }
    }

    @Override
    public VQoSFlowDescription decode(OctetInputStream stream) {
        var res = new VQoSFlowDescription();
        res.qfi = EQoSFlowIdentifier.fromValue(stream.readOctetI() & 0b111111);
        res.operationCode = EOperationCode.fromValue((stream.readOctetI() >> 5) & 0b111);
        res.numberOfParameters = new Bit6(stream.peekOctetI() & 0b111111);
        res.eBit = new Bit(stream.readOctet().getBit(6));
        res.parametersList = new VParameter[res.numberOfParameters.intValue()];
        for (int i = 0; i < res.parametersList.length; i++) {
            res.parametersList[i] = new VParameter().decode(stream);
        }
        return res;
    }

    public static class VParameter extends NasValue {
        public Octet identifier;
        public OctetString content;

        @Override
        public void encode(OctetOutputStream stream) {
            stream.writeOctet(identifier);
            stream.writeOctet(content.length);
            stream.writeOctetString(content);
        }

        @Override
        public VParameter decode(OctetInputStream stream) {
            var res = new VParameter();
            res.identifier = stream.readOctet();
            res.content = stream.readOctetString(stream.readOctetI());
            return res;
        }
    }

    public static class EOperationCode extends ProtocolEnum {
        public static final EOperationCode CREATE_NEW = new EOperationCode(0b001, "Create new QoS flow description");
        public static final EOperationCode DELETE_EXISTING = new EOperationCode(0b010, "Delete existing QoS flow description");
        public static final EOperationCode MODIFY_EXISTING = new EOperationCode(0b011, "Modify existing QoS flow description");

        private EOperationCode(int value, String name) {
            super(value, name);
        }

        public static EOperationCode fromValue(int value) {
            return fromValueGeneric(EOperationCode.class, value, null);
        }
    }
}
