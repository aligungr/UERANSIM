/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.asn;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;

public abstract class AsnMetaData {

    public abstract Class[] getJavaTypesById();

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
