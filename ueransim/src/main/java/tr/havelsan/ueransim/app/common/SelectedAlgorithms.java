/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common;

import tr.havelsan.ueransim.nas.impl.enums.ETypeOfCipheringAlgorithm;
import tr.havelsan.ueransim.nas.impl.enums.ETypeOfIntegrityProtectionAlgorithm;

public class SelectedAlgorithms {
    public final ETypeOfIntegrityProtectionAlgorithm integrity;
    public final ETypeOfCipheringAlgorithm ciphering;

    public SelectedAlgorithms(ETypeOfIntegrityProtectionAlgorithm integrity, ETypeOfCipheringAlgorithm ciphering) {
        this.integrity = integrity;
        this.ciphering = ciphering;
    }
}
