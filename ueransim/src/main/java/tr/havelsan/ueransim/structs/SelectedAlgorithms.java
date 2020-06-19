package tr.havelsan.ueransim.structs;

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
