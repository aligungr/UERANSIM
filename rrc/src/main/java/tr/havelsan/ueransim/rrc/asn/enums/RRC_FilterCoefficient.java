/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.enums;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;

public class RRC_FilterCoefficient extends AsnEnumerated {
    public static final RRC_FilterCoefficient FC0 = new RRC_FilterCoefficient(0);
    public static final RRC_FilterCoefficient FC1 = new RRC_FilterCoefficient(1);
    public static final RRC_FilterCoefficient FC2 = new RRC_FilterCoefficient(2);
    public static final RRC_FilterCoefficient FC3 = new RRC_FilterCoefficient(3);
    public static final RRC_FilterCoefficient FC4 = new RRC_FilterCoefficient(4);
    public static final RRC_FilterCoefficient FC5 = new RRC_FilterCoefficient(5);
    public static final RRC_FilterCoefficient FC6 = new RRC_FilterCoefficient(6);
    public static final RRC_FilterCoefficient FC7 = new RRC_FilterCoefficient(7);
    public static final RRC_FilterCoefficient FC8 = new RRC_FilterCoefficient(8);
    public static final RRC_FilterCoefficient FC9 = new RRC_FilterCoefficient(9);
    public static final RRC_FilterCoefficient FC11 = new RRC_FilterCoefficient(10);
    public static final RRC_FilterCoefficient FC13 = new RRC_FilterCoefficient(11);
    public static final RRC_FilterCoefficient FC15 = new RRC_FilterCoefficient(12);
    public static final RRC_FilterCoefficient FC17 = new RRC_FilterCoefficient(13);
    public static final RRC_FilterCoefficient FC19 = new RRC_FilterCoefficient(14);
    public static final RRC_FilterCoefficient SPARE1 = new RRC_FilterCoefficient(15);

    private RRC_FilterCoefficient(long value) {
        super(value);
    }
}

