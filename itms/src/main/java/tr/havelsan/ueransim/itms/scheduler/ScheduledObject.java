/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.itms.scheduler;

public class ScheduledObject {
    public final int arg0;
    public final int arg1;
    public final Object obj;

    public ScheduledObject(int arg0, int arg1, Object obj) {
        this.arg0 = arg0;
        this.arg1 = arg1;
        this.obj = obj;
    }
}
