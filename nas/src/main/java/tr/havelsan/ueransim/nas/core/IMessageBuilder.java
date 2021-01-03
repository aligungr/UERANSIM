/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
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
