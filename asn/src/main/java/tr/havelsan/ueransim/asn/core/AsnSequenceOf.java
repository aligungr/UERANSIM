/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.asn.core;

import java.util.ArrayList;
import java.util.List;

public class AsnSequenceOf<T extends AsnValue> extends AsnValue {

    public final List<T> list;

    public AsnSequenceOf() {
        this.list = new ArrayList<>();
    }
}
