/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.l2.rlc.entity;

import tr.havelsan.ueransim.utils.octets.OctetString;

public abstract class RlcEntity {

    public abstract OctetString createPdu(OctetString sdu);

    public abstract void receivePdu(OctetString data);

    public abstract void receiveSdu(OctetString data);
}
