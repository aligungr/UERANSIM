/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.impl.ies;

import tr.havelsan.ueransim.nas.core.ProtocolEnum;
import tr.havelsan.ueransim.nas.core.ies.InformationElement4;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.octets.Octet;

public class IE5gsUpdateType extends InformationElement4 {
    public ESmsRequested smsRequested;
    public ENgRanRadioCapabilityUpdate ngRanRcu;

    public IE5gsUpdateType() {
    }

    public IE5gsUpdateType(ESmsRequested smsRequested, ENgRanRadioCapabilityUpdate ngRanRcu) {
        this.smsRequested = smsRequested;
        this.ngRanRcu = ngRanRcu;
    }

    @Override
    protected IE5gsUpdateType decodeIE4(OctetInputStream stream, int length) {
        var res = new IE5gsUpdateType();
        res.smsRequested = ESmsRequested.fromValue(stream.peekOctetI() & 0b1);
        res.ngRanRcu = ENgRanRadioCapabilityUpdate.fromValue(stream.readOctetI() >> 1 & 0b1);
        return res;
    }

    @Override
    public void encodeIE4(OctetOutputStream stream) {
        var octet = new Octet();
        octet = octet.setBit(0, smsRequested.intValue());
        octet = octet.setBit(1, ngRanRcu.intValue());
        stream.writeOctet(octet);
    }

    public static class ENgRanRadioCapabilityUpdate extends ProtocolEnum {
        public static final ENgRanRadioCapabilityUpdate NOT_NEEDED
                = new ENgRanRadioCapabilityUpdate(0b0, "NG-RAN radio capability update not needed");
        public static final ENgRanRadioCapabilityUpdate NEEDED
                = new ENgRanRadioCapabilityUpdate(0b1, "NG-RAN radio capability update needed");

        private ENgRanRadioCapabilityUpdate(int value, String name) {
            super(value, name);
        }

        public static ENgRanRadioCapabilityUpdate fromValue(int value) {
            return fromValueGeneric(ENgRanRadioCapabilityUpdate.class, value, null);
        }
    }

    public static class ESmsRequested extends ProtocolEnum {
        public static final ESmsRequested NOT_SUPPORTED
                = new ESmsRequested(0b0, "SMS over NAS not supported");
        public static final ESmsRequested SUPPORTED
                = new ESmsRequested(0b1, "SMS over NAS supported");

        private ESmsRequested(int value, String name) {
            super(value, name);
        }

        public static ESmsRequested fromValue(int value) {
            return fromValueGeneric(ESmsRequested.class, value, null);
        }
    }
}
