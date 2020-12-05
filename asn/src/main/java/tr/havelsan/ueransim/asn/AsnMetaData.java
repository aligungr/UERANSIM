/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.asn;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.interop.AsnMeta_TypeDesc;
import tr.havelsan.ueransim.utils.Json;

import java.util.HashMap;
import java.util.Map;

public class AsnMetaData {

    private final Class[] idOrderedTypes;
    private final Map<Class, Integer> typeToId;
    private final AsnMeta_TypeDesc[] typeDescs;

    public AsnMetaData(String classJson, String dataJson) {
        try {
            String[] classes = Json.fromJson(classJson, String[].class);
            AsnMeta_TypeDesc[] data = Json.fromJson(dataJson, AsnMeta_TypeDesc[].class);

            idOrderedTypes = new Class[classes.length];
            typeToId = new HashMap<>();
            typeDescs = new AsnMeta_TypeDesc[data.length + 1];

            for (int i = 0; i < classes.length; i++) {
                if (classes[i] != null && classes[i].length() > 0) {
                    idOrderedTypes[i] = Class.forName(classes[i]);
                    typeToId.put(idOrderedTypes[i], i);
                }
            }

            for (var x : idOrderedTypes) {
                if (!typeToId.containsKey(x)) {
                    System.err.println(x);
                }
            }

            for (var typeDesc : data) {
                typeDescs[typeDesc.type_info_id] = typeDesc;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Class idToClass(int id) {
        return idOrderedTypes[id];
    }

    public int classToId(Class c) {
        return typeToId.getOrDefault(c, 0);
    }

    public String enumString(Class type, long value) {
        return null;
    }

    public String enumString(AsnEnumerated enumerated) {
        return enumString(enumerated.getClass(), enumerated.intValue);
    }

    public String[] memberIdentifiers(Class type) {
        return null;
    }

    public String[] memberAsnNames(Class type) {
        return null;
    }

    public Class elementType(Class listType) {
        return null;
    }

    public String xmlTagName(Class itemType) {
        return null;
    }
}
