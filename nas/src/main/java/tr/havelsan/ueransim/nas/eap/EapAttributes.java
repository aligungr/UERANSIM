package tr.havelsan.ueransim.nas.eap;

import tr.havelsan.ueransim.utils.octets.OctetString;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public final class EapAttributes {
    private final LinkedHashMap<EAttributeType, OctetString> attributes;

    public EapAttributes() {
        this.attributes = new LinkedHashMap<>();
    }

    //======================================================================================================
    //                                        GET METHODS
    //======================================================================================================

    public OctetString getRand() {
        return attributes.get(EAttributeType.AT_RAND);
    }

    public OctetString getMac() {
        return attributes.get(EAttributeType.AT_MAC);
    }

    //======================================================================================================
    //                                          PUT METHODS
    //======================================================================================================

    public void putRes(OctetString value) {
        attributes.put(EAttributeType.AT_RES, value);
    }

    public void putMac(OctetString value) {
        attributes.put(EAttributeType.AT_MAC, value);
    }

    public void putKdf(OctetString value) {
        attributes.put(EAttributeType.AT_KDF, value);
    }

    //======================================================================================================
    //                                          OTHERS
    //======================================================================================================


    public void putRawAttribute(EAttributeType key, OctetString value) {
        attributes.put(key, value);
    }

    public Set<Map.Entry<EAttributeType, OctetString>> entrySet() {
        return attributes.entrySet();
    }
}