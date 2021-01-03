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
import tr.havelsan.ueransim.nas.impl.values.VPlmn;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.exceptions.IncorrectImplementationException;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.Octet2;
import tr.havelsan.ueransim.utils.octets.OctetString;

import java.util.Arrays;

public class IESorTransparentContainer extends InformationElement6 {
    public ESorDataType sorDataType;

    public IESorTransparentContainer() {
    }

    public IESorTransparentContainer(ESorDataType sorDataType) {
        this.sorDataType = sorDataType;
    }

    @Override
    protected final IESorTransparentContainer decodeIE6(OctetInputStream stream, int length) {
        var header = stream.readOctet();
        var sorDataType = ESorDataType.fromValue(header.getBitI(0));

        if (sorDataType.equals(ESorDataType.STEERING_OF_ROAMING_INFORMATION)) {
            var listIndication = EListIndication.fromValue(header.getBitI(1));
            var listType = EListType.fromValue(header.getBitI(2));
            var ack = EAcknowledgement.fromValue(header.getBitI(3));
            var sorMacIAusf = stream.readOctetString(16);
            var counterSor = stream.readOctet2();

            if (listType.equals(EListType.SECURED_PACKET)) {
                var res = new IESecuredPacket();
                res.sorDataType = sorDataType;
                res.listIndication = listIndication;
                res.listType = listType;
                res.ack = ack;
                res.sorMacIAusf = sorMacIAusf;
                res.counterSor = counterSor;
                res.securedPacket = stream.readOctetString(length - 19);
                return res;
            } else {
                var res = new IEPlmnIdAccessTech();
                res.sorDataType = sorDataType;
                res.listIndication = listIndication;
                res.listType = listType;
                res.ack = ack;
                res.sorMacIAusf = sorMacIAusf;
                res.counterSor = counterSor;
                res.list = Utils.decodeList(stream, octetInputStream -> new VPlmnIdAccessTech().decode(octetInputStream), length - 19, VPlmnIdAccessTech.class);
                return res;
            }
        } else {
            var res = new IEAcknowledgement();
            res.sorDataType = sorDataType;
            res.sorMacIUe = stream.readOctetString(16);
            return res;
        }
    }

    @Override
    public void encodeIE6(OctetOutputStream stream) {
        throw new IncorrectImplementationException("sub types must override this method.");
    }

    public static class IEAcknowledgement extends IESorTransparentContainer {
        public OctetString sorMacIUe;

        @Override
        public void encodeIE6(OctetOutputStream stream) {
            stream.writeOctet(0b00000001); // flags
            stream.writeOctetString(sorMacIUe);
        }
    }

    public static class IESteeringOfRoamingInformation extends IESorTransparentContainer {
        public EListIndication listIndication;
        public EListType listType;
        public EAcknowledgement ack;

        public OctetString sorMacIAusf;
        public Octet2 counterSor;

        public IESteeringOfRoamingInformation() {
        }

        public IESteeringOfRoamingInformation(EListIndication listIndication, EListType listType, EAcknowledgement ack, OctetString sorMacIAusf, Octet2 counterSor) {
            this.listIndication = listIndication;
            this.listType = listType;
            this.ack = ack;
            this.sorMacIAusf = sorMacIAusf;
            this.counterSor = counterSor;
        }

        @Override
        public void encodeIE6(OctetOutputStream stream) {
            var flags = new Octet();
            flags = flags.setBit(0, sorDataType.intValue());
            flags = flags.setBit(1, listIndication.intValue());
            flags = flags.setBit(2, listType.intValue());
            flags = flags.setBit(3, ack.intValue());

            stream.writeOctet(flags);
            stream.writeOctetString(sorMacIAusf);
            stream.writeOctet2(counterSor);
        }
    }

    public static class IESecuredPacket extends IESteeringOfRoamingInformation {
        public OctetString securedPacket;

        public IESecuredPacket() {
        }

        public IESecuredPacket(OctetString securedPacket) {
            this.securedPacket = securedPacket;
        }

        @Override
        public void encodeIE6(OctetOutputStream stream) {
            super.encodeIE6(stream);
            stream.writeOctetString(securedPacket);
        }
    }

    public static class IEPlmnIdAccessTech extends IESteeringOfRoamingInformation {
        public VPlmnIdAccessTech[] list;

        public IEPlmnIdAccessTech() {
        }

        public IEPlmnIdAccessTech(VPlmnIdAccessTech[] list) {
            this.list = list;
        }

        @Override
        public void encodeIE6(OctetOutputStream stream) {
            super.encodeIE6(stream);
            Arrays.stream(list).forEach(item -> item.encode(stream));
        }
    }

    public static class VPlmnIdAccessTech extends NasValue {
        public VPlmn plmnId;
        public Octet2 accessTechnologyIdentifier;

        public VPlmnIdAccessTech() {
        }

        public VPlmnIdAccessTech(VPlmn plmnId, Octet2 accessTechnologyIdentifier) {
            this.plmnId = plmnId;
            this.accessTechnologyIdentifier = accessTechnologyIdentifier;
        }

        @Override
        public VPlmnIdAccessTech decode(OctetInputStream stream) {
            var res = new VPlmnIdAccessTech();
            res.plmnId = new VPlmn().decode(stream);
            res.accessTechnologyIdentifier = stream.readOctet2();
            return res;
        }

        @Override
        public void encode(OctetOutputStream stream) {
            plmnId.encode(stream);
            stream.writeOctet2(accessTechnologyIdentifier);
        }
    }

    public static class ESorDataType extends ProtocolEnum {
        public static final ESorDataType STEERING_OF_ROAMING_INFORMATION
                = new ESorDataType(0b0, "The SOR transparent container carries steering of roaming information");
        public static final ESorDataType ACKNOWLEDGEMENT
                = new ESorDataType(0b1, "The SOR transparent container carries acknowledgement of successful reception of the steering of roaming information");

        private ESorDataType(int value, String name) {
            super(value, name);
        }

        public static ESorDataType fromValue(int value) {
            return fromValueGeneric(ESorDataType.class, value, null);
        }
    }

    public static class EListIndication extends ProtocolEnum {
        public static final EListIndication NOT_PROVIDED
                = new EListIndication(0b0, "HPLMN indication that 'no change of the \"Operator Controlled PLMN Selector with Access Technology\" list stored in the UE is needed and thus no list of preferred PLMN/access technology combinations is provided'");
        public static final EListIndication PROVIDED
                = new EListIndication(0b1, "list of preferred PLMN/access technology combinations is provided");

        private EListIndication(int value, String name) {
            super(value, name);
        }

        public static EListIndication fromValue(int value) {
            return fromValueGeneric(EListIndication.class, value, null);
        }
    }

    public static class EListType extends ProtocolEnum {
        public static final EListType SECURED_PACKET
                = new EListType(0b0, "The list type is a secured packet");
        public static final EListType PLMN_ID_AND_ACCESS_TECH
                = new EListType(0b1, "The list type is a \"PLMN ID and access technology list\"");

        private EListType(int value, String name) {
            super(value, name);
        }

        public static EListType fromValue(int value) {
            return fromValueGeneric(EListType.class, value, null);
        }
    }

    public static class EAcknowledgement extends ProtocolEnum {
        public static final EAcknowledgement NOT_REQUESTED
                = new EAcknowledgement(0b0, "acknowledgement not requested");
        public static final EAcknowledgement REQUESTED
                = new EAcknowledgement(0b1, "acknowledgement requested");

        private EAcknowledgement(int value, String name) {
            super(value, name);
        }

        public static EAcknowledgement fromValue(int value) {
            return fromValueGeneric(EAcknowledgement.class, value, null);
        }
    }
}
