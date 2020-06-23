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
 *
 * @author Ali Güngör (aligng1620@gmail.com)
 */

package tr.havelsan.ueransim.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BiMap<K, V> {
    private final Map<K, V> keys;
    private final Map<V, K> values;

    public BiMap() {
        this.keys = new HashMap<>();
        this.values = new HashMap<>();
    }

    public boolean containsKey(K key) {
        return keys.containsKey(key);
    }

    public boolean containsValue(V value) {
        return values.containsKey(value);
    }

    public void put(K key, V value) {
        keys.put(key, value);
        values.put(value, key);
    }

    public K getKey(V value) {
        return values.get(value);
    }

    public V getValue(K key) {
        return keys.get(key);
    }

    public int size() {
        return keys.size();
    }

    public Set<K> keySet() {
        return keys.keySet();
    }

    public Set<V> valueSet() {
        return values.keySet();
    }
}
