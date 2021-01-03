/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.asn;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.interop.AsnMeta_Constants;
import tr.havelsan.ueransim.asn.interop.AsnMeta_TypeDesc;
import tr.havelsan.ueransim.utils.Json;

import java.util.HashMap;
import java.util.Map;

public class AsnMetaData {

    final Class[] idToType;
    final Map<Class, Integer> typeToId;
    final AsnMeta_TypeDesc[] typeDescs;

    public AsnMetaData(String classJson, String dataJson) {
        try {
            String[] classes = Json.fromJson(classJson, String[].class);
            AsnMeta_TypeDesc[] data = Json.fromJson(dataJson, AsnMeta_TypeDesc[].class);

            idToType = new Class[classes.length];
            typeToId = new HashMap<>();
            typeDescs = new AsnMeta_TypeDesc[data.length + 1];

            for (int i = 0; i < classes.length; i++) {
                if (classes[i] != null && classes[i].length() > 0) {
                    idToType[i] = Class.forName(classes[i]);
                    typeToId.put(idToType[i], i);
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
        return idToType[id];
    }

    public int classToId(Class c) {
        return typeToId.getOrDefault(c, 0);
    }

    public String enumString(Class type, long value) {
        var desc = typeDescs[classToId(type)];
        if (desc.asn_type != AsnMeta_Constants.ASN_TYPE_native_enumerated) {
            throw new RuntimeException("type is not an enumerated");
        }

        for (var pair : desc.int_specs.enums) {
            if ((long) Double.parseDouble(pair.get(1).toString()) == value)
                return pair.get(0).toString();
        }

        throw new RuntimeException("enumerated value not found for type " + type + " and value " + value);
    }

    public String enumString(AsnEnumerated enumerated) {
        return enumString(enumerated.getClass(), enumerated.intValue);
    }

    public String[] memberIdentifiers(Class type) {
        var desc = typeDescs[classToId(type)];
        var members = desc.members;

        var res = new String[members.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = members.get(i).asn_identifier.replace("-", "_").replace(" ", "_");
        }

        return res;
    }

    public String[] memberAsnNames(Class type) {
        var desc = typeDescs[classToId(type)];
        var members = desc.members;

        var res = new String[members.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = members.get(i).asn_identifier;
        }

        return res;
    }

    public Class elementType(Class listType) {
        return idToType[typeDescs[classToId(listType)].members.get(0).type_id];
    }

    public String xmlTagName(Class type) {
        return typeDescs[classToId(type)].xml_name;
    }
}
