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

package tr.havelsan.ueransim.nas.core;

import tr.havelsan.ueransim.nas.core.ies.*;

public interface IMessageBuilder {

    /**
     * Registers a mandatory information element with type 2,3,4 or 6. For information element type 1,
     * use the {@link #mandatoryIE1} method instead.
     *
     * @param field Java field name of the class of the relevant information element. That field
     *              must be public and its type must be {@link InformationElement2}, {@link InformationElement3}, {@link InformationElement4}, {@link InformationElement6} or derived types.
     */
    void mandatoryIE(String field);

    /**
     * Registers an optional information element with type 2,3,4 or 6. For information element type 1,
     * use the {@link #optionalIE1} method instead.
     *
     * @param iei   Information element identifier for the field. This value must be 8 bit (one octet)
     * @param field Java field name of the class of the relevant information element. That field
     *              must be public and its type must be {@link InformationElement2}, {@link InformationElement3}, {@link InformationElement4}, {@link InformationElement6} or derived types.
     */
    void optionalIE(int iei, String field);

    /**
     * Registers <b>two</b> mandatory information elements with type 1.
     *
     * @param field1 Java field name of the class of the relevant information element. That field
     *               must be public and its type must be {@link InformationElement1} or derived types.
     *               This field is the most significant 4 bits of the octet.
     *               <code>null</code> value can be passed for spare half octets.
     * @param field0 Java field name of the class of the relevant information element. That field
     *               must be public and its type must be {@link InformationElement1} or derived types.
     *               This field is the least significant 4 bits of the octet.
     *               <code>null</code> value can be passed for spare half octets.
     */
    void mandatoryIE1(String field1, String field0);

    /**
     * Registers <b>one</b> mandatory information element with type 1 with a spare half octet.
     * Information element is the least significant 4 bits of the octet.
     *
     * @param field Java field name of the class of the relevant information element. That field
     *              must be public and its type must be {@link InformationElement1} or derived types.
     *              This field is the least significant 4 bits of the octet.
     */
    void mandatoryIE1(String field);

    /**
     * Registers an optional information element with type 1.
     *
     * @param iei   Information element identifier for the field. This value must be 4 bit (half octet)
     * @param field Java field name of the class of the relevant information element. That field
     *              must be public and its type must be {@link InformationElement1} or derived types.
     */
    void optionalIE1(int iei, String field);
}
