/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.mts;

import java.util.LinkedHashMap;

public final class ImplicitTypedObject {
    private final LinkedHashMap<String, Object> parameters;

    public ImplicitTypedObject(LinkedHashMap<String, Object> parameters) {
        this.parameters = parameters;
    }

    public LinkedHashMap<String, Object> getParameters() {
        return parameters;
    }

    public Object get(String key) {
        return getParameters().get(key);
    }

    public long getLong(String key) {
        return ((Number) get(key)).longValue();
    }

    public int getInt(String key) {
        return Math.toIntExact(getLong(key));
    }

    public boolean getBool(String key) {
        return (boolean) get(key);
    }

    public String getString(String key) {
        var value = get(key);
        return value == null ? null : String.valueOf(value);
    }

    public <T> T getConstructed(MtsContext mts, String key, Class<T> type) {
        return mts.constructor.construct(type, (ImplicitTypedObject) get(key), true);
    }

    public <T> T asConstructed(MtsContext mts, Class<T> type) {
        return mts.constructor.construct(type, this, true);
    }

    public <T> T getConverted(MtsContext mts,String key, Class<T> type) {
        return (T) mts.converter.convert(get(key), type, true).get(0).value;
    }
}
