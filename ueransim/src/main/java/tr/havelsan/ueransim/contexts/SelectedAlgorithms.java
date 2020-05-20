package tr.havelsan.ueransim.contexts;

import tr.havelsan.ueransim.nas.impl.enums.ETypeOfCipheringAlgorithm;
import tr.havelsan.ueransim.nas.impl.enums.ETypeOfIntegrityProtectionAlgorithm;

public class SelectedAlgorithms {
    public ETypeOfIntegrityProtectionAlgorithm integrity;
    public ETypeOfCipheringAlgorithm ciphering;

    public SelectedAlgorithms() {
        integrity = ETypeOfIntegrityProtectionAlgorithm.IA0;
        ciphering = ETypeOfCipheringAlgorithm.EA0;
    }
}
