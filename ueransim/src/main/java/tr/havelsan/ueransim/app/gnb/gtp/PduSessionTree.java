/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.app.gnb.gtp;

import tr.havelsan.ueransim.app.common.PduSessionResource;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PduSessionTree {

    private final Map<Long, PduSessionResource> mapByDownTeid;
    private final Map<UUID, Map<Integer, PduSessionResource>> mapByUeId;

    public PduSessionTree() {
        this.mapByDownTeid = new HashMap<>();
        this.mapByUeId = new HashMap<>();
    }

    public void insert(PduSessionResource session) {
        mapByDownTeid.put(session.downLayer.gTPTunnel.gTP_TEID.value.get4(0).longValue(), session);

        var mapBySessionId = mapByUeId.computeIfAbsent(session.ueId, k -> new HashMap<>());
        mapBySessionId.put(session.pduSessionId, session);
    }

    public PduSessionResource findByDownTeid(long upTeid) {
        return mapByDownTeid.get(upTeid);
    }

    public PduSessionResource findBySessionId(UUID ue, int psi) {
        var mapBySessionId = mapByUeId.get(ue);
        if (mapBySessionId != null) {
            return mapBySessionId.get(psi);
        }
        return null;
    }

    public void delete(PduSessionResource session) {
        mapByDownTeid.remove(session.upLayer.gTPTunnel.gTP_TEID.value.get4(0).longValue());

        var mapBySessionId = mapByUeId.get(session.ueId);
        if (mapBySessionId != null) {
            mapBySessionId.remove(session.pduSessionId);
        }
    }
}
