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

import tr.havelsan.ueransim.nas.core.ProtocolEnum;

public class ESubType extends ProtocolEnum {
    public static ESubType AKA_CHALLENGE
            = new ESubType(1, "AKA-Challenge");
    public static ESubType AKA_AUTHENTICATION_REJECT
            = new ESubType(2, "AKA-Authentication-Reject");
    public static ESubType AKA_SYNCHRONIZATION_FAILURE
            = new ESubType(4, "AKA-Synchronization-Failure");
    public static ESubType AKA_IDENTITY
            = new ESubType(5, "AKA-Identity");
    public static ESubType AKA_NOTIFICATION
            = new ESubType(12, "Notification");
    public static ESubType AKA_REAUTHENTICATION
            = new ESubType(13, "Re-authentication");
    public static ESubType AKA_CLIENT_ERROR
            = new ESubType(14, "Client-Error");

    private ESubType(int value, String name) {
        super(value, name);
    }

    public static ESubType fromValue(int value) {
        return fromValueGeneric(ESubType.class, value, null);
    }
}