/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.ngap0.core;

public abstract class NGAP_Enumerated extends NGAP_Value {

    public final String sValue;

    protected NGAP_Enumerated(String sValue) {
        this.sValue = sValue;
    }
}
