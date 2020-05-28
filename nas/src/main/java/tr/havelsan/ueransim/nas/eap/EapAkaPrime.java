package tr.havelsan.ueransim.nas.eap;

import tr.havelsan.ueransim.utils.octets.Octet;

public class EapAkaPrime extends Eap {
    public ESubType subType;
    public EapAttributes attributes;

    public EapAkaPrime(ECode code, Octet id) {
        super(code, id, EEapType.EAP_AKA_PRIME);
        this.attributes = new EapAttributes();
    }

    public EapAkaPrime(ECode code, Octet id, ESubType subType, EapAttributes attributes) {
        super(code, id, EEapType.EAP_AKA_PRIME);
        this.subType = subType;
        this.attributes = attributes;
    }
}
