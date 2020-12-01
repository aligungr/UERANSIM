/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.core;

import java.util.ArrayList;
import java.util.List;

public abstract class RRC_SequenceOf<T extends RRC_Value> extends RRC_Value {

    public List<T> list;

    public RRC_SequenceOf() {
        this(new ArrayList<>());
    }

    public RRC_SequenceOf(List<T> list) {
        this.list = list;
    }

    public abstract Class<T> getItemType();
}

