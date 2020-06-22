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

package tr.havelsan.ueransim.nas.eap;

import tr.havelsan.ueransim.utils.Utils;
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

    //======================================================================================================
    //                                        GET METHODS
    //======================================================================================================

    public OctetString getRand() {
        return attributes.get(EAttributeType.AT_RAND).substring(2);
    }

    public OctetString getMac() {
        return attributes.get(EAttributeType.AT_MAC).substring(2);
    }

    public OctetString getAutn() {
        return attributes.get(EAttributeType.AT_AUTN).substring(2);
    }

    public int getClientErrorCode() {
        return attributes.get(EAttributeType.AT_CLIENT_ERROR_CODE).get2(0).intValue();
    }

    public int getKdf() {
        return attributes.get(EAttributeType.AT_KDF).get2(0).intValue();
    }

    public OctetString getAuts() {
        return attributes.get(EAttributeType.AT_AUTS);
    }

    //======================================================================================================
    //                                          PUT METHODS
    //======================================================================================================

    public void putRes(OctetString value) {
        attributes.put(EAttributeType.AT_RES, Utils.insertLeadingLength2(value, true));
    }

    public void putMac(OctetString value) {
        attributes.put(EAttributeType.AT_MAC, OctetString.concat(new OctetString("0000"), value));
    }

    public void putAutn(OctetString value) {
        attributes.put(EAttributeType.AT_AUTN, OctetString.concat(new OctetString("0000"), value));
    }

    public void putKdf(int value) {
        attributes.put(EAttributeType.AT_KDF, new Octet2(value).toOctetString());
    }

    public void putClientErrorCode(int code) {
        attributes.put(EAttributeType.AT_CLIENT_ERROR_CODE, new Octet2(code).toOctetString());
    }

    public void putAuts(OctetString auts) {
        attributes.put(EAttributeType.AT_AUTS, auts);
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