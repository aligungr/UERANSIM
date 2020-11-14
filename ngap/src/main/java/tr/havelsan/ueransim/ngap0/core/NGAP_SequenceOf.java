/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.ngap0.core;

import java.util.ArrayList;
import java.util.List;

public abstract class NGAP_SequenceOf<T extends NGAP_Value> extends NGAP_Value {

    public List<T> list;

    public NGAP_SequenceOf() {
        this(new ArrayList<>());
    }

    public NGAP_SequenceOf(List<T> list) {
        this.list = list;
    }

    public abstract Class<T> getItemType();
}
