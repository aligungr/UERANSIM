/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc;

import tr.havelsan.ueransim.asn.AsnMetaData;
import tr.havelsan.ueransim.asn.XerDecoder;
import tr.havelsan.ueransim.asn.XerEncoder;
import tr.havelsan.ueransim.asn.core.AsnValue;
import tr.havelsan.ueransim.utils.octets.OctetString;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class RrcEncoding {

    private static final AsnMetaData metaData = new AsnMetaData(
            getResourceString("RRC_asn_class.json"),
            getResourceString("RRC_asn_data.json")
    );
    private static final XerEncoder xerEncoder = new XerEncoder(metaData);
    private static final XerDecoder xerDecoder = new XerDecoder(metaData);

    private static String getResourceString(String name) {
        byte[] data;

        try (var stream = RrcEncoding.class.getClassLoader().getResourceAsStream(name)) {
            if (stream == null)
                throw new RuntimeException("resource not found: " + name);
            data = stream.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new String(data, StandardCharsets.UTF_8);
    }

    public static AsnValue decodeUper(byte[] pdu, RrcDataUnitType type) {
        return xerDecoder.decode(RrcJni.uperToXer(pdu, type), type.getPodType());
    }

    public static AsnValue decodeUper(OctetString pdu, RrcDataUnitType type) {
        return decodeUper(pdu.toByteArray(), type);
    }

    public static byte[] encodeUper(AsnValue value, RrcDataUnitType type) {
        return RrcJni.xerToUper(xerEncoder.encode(value), type);
    }

    public static OctetString encodeUperS(AsnValue value, RrcDataUnitType type) {
        return new OctetString(encodeUper(value, type));
    }
}