package tr.havelsan.ueransim.nas.eap;

import tr.havelsan.ueransim.core.exceptions.NotImplementedException;
import tr.havelsan.ueransim.utils.octets.Octet2;
import tr.havelsan.ueransim.utils.octets.OctetString;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public final class EapAttributes {
    private final LinkedHashMap<EAttributeType, OctetString> attributes;

    public EapAttributes() {
        this.attributes = new LinkedHashMap<>();
    }

    public OctetString getAttribute(EAttributeType key) {
        if (key.equals(EAttributeType.AT_RAND)) {
            return attributes.get(key).substring(2); // 2 octets reserved
        }
        if (key.equals(EAttributeType.AT_MAC)) {
            return attributes.get(key).substring(2); // 2 octets reserved
        }
        if (key.equals(EAttributeType.AT_KDF)) {
            return attributes.get(key);
        }
        if (key.equals(EAttributeType.AT_RES)) {
            var val = attributes.get(key);
            if (val.length < 2 || (val.length - 2) % 4 != 0) {
                throw new IllegalStateException("RES value length is incorrect");
            }
            if (val.length - 2 != val.get2(0).intValue()) {
                throw new IllegalStateException("RES value length is incorrect");
            }
            return val.substring(2);
        }

        throw new NotImplementedException("EapAkaPrime attribute type not implemented: " + key);
    }

    public void putAttribute(EAttributeType key, OctetString value) {
        if (key.equals(EAttributeType.AT_RAND)) {
            attributes.put(key, OctetString.concat(new OctetString("0000"), value));
        }
        if (key.equals(EAttributeType.AT_MAC)) {
            attributes.put(key, OctetString.concat(new OctetString("0000"), value));
        }
        if (key.equals(EAttributeType.AT_KDF)) {
            if (value.length != 2) {
                throw new IllegalArgumentException("KDF value length is incorrect");
            }
            attributes.put(key, value);
        }
        if (key.equals(EAttributeType.AT_RES)) {
            if (value.length > 65535 || value.length % 4 != 0) {
                throw new IllegalArgumentException("RES value length is incorrect");
            }
            value = OctetString.concat(new Octet2(value.length).toOctetString(), value);
        }

        attributes.put(key, value);
    }

    public Set<Map.Entry<EAttributeType, OctetString>> entrySet() {
        return attributes.entrySet();
    }
}