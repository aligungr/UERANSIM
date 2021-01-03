/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.eap;

import tr.havelsan.ueransim.utils.octets.Octet;

public class EapAkaPrime extends Eap {
    public ESubType subType;
    public EapAttributes attributes;

    public EapAkaPrime(ECode code, Octet id, ESubType subType) {
        super(code, id, EEapType.EAP_AKA_PRIME);
        this.subType = subType;
        this.attributes = new EapAttributes();
    }

    public EapAkaPrime makeCopy() {
        var copy = new EapAkaPrime(this.code, this.id, this.subType);
        for (var entry : this.attributes.entrySet()) {
            copy.attributes.putRawAttribute(entry.getKey(), entry.getValue());
        }
        return copy;
    }
}
