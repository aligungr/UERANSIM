/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
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
