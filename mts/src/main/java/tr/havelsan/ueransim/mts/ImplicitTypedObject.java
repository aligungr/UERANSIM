/*
 * MIT License
 *
 * Copyright (c) 2020 ALİ GÜNGÖR
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
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
