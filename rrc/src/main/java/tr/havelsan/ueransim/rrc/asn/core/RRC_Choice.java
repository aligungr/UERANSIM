/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.core;

public abstract class RRC_Choice extends RRC_Value {

    public abstract String[] getMemberNames();

    public abstract String[] getMemberIdentifiers();

    public RRC_Value getPresentValue() {
        var cls = getClass();
        for (String id : getMemberIdentifiers()) {
            try {
                var field = cls.getField(id);
                var value = field.get(this);
                if (value != null) return (RRC_Value) value;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public void setPresentValue(RRC_Value value) {
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

    @Override
    public String getAsnName() {
        return "CHOICE";
    }

    @Override
    public String getXmlTagName() {
        return "CHOICE";
    }
}
