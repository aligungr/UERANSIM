/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.enumerations;

import tr.havelsan.ueransim.ngap0.core.NGAP_Enumerated;

public class NGAP_PagingPriority extends NGAP_Enumerated {

    public static final NGAP_PagingPriority PRIOLEVEL1 = new NGAP_PagingPriority("priolevel1");
    public static final NGAP_PagingPriority PRIOLEVEL2 = new NGAP_PagingPriority("priolevel2");
    public static final NGAP_PagingPriority PRIOLEVEL3 = new NGAP_PagingPriority("priolevel3");
    public static final NGAP_PagingPriority PRIOLEVEL4 = new NGAP_PagingPriority("priolevel4");
    public static final NGAP_PagingPriority PRIOLEVEL5 = new NGAP_PagingPriority("priolevel5");
    public static final NGAP_PagingPriority PRIOLEVEL6 = new NGAP_PagingPriority("priolevel6");
    public static final NGAP_PagingPriority PRIOLEVEL7 = new NGAP_PagingPriority("priolevel7");
    public static final NGAP_PagingPriority PRIOLEVEL8 = new NGAP_PagingPriority("priolevel8");

    protected NGAP_PagingPriority(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "PagingPriority";
    }

    @Override
    public String getXmlTagName() {
        return "PagingPriority";
    }
}
