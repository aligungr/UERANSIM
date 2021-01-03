/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.core;

public abstract class NGAP_Choice extends NGAP_Value {

    public abstract String[] getMemberNames();

    public abstract String[] getMemberIdentifiers();

    public NGAP_Value getPresentValue() {
        var cls = getClass();
        for (String id : getMemberIdentifiers()) {
            try {
                var field = cls.getField(id);
                var value = field.get(this);
                if (value != null) return (NGAP_Value) value;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public void setPresentValue(NGAP_Value value) {
        var cls = getClass();
        for (String id : getMemberIdentifiers()) {
            try {
                var field = cls.getField(id);
                if (field.getType() == value.getClass()) {
                    field.set(this, value);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
