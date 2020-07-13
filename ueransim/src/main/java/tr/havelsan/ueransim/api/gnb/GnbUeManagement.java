/*
 * MIT License
 *
 * Copyright (c) 2020 ALİ GÜNGÖR
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * @author Ali Güngör (aligng1620@gmail.com)
 */

package tr.havelsan.ueransim.api.gnb;

import tr.havelsan.ueransim.core.GnbSimContext;
import tr.havelsan.ueransim.structs.GnbUeContext;
import tr.havelsan.ueransim.structs.Guami;
import tr.havelsan.ueransim.utils.Debugging;
import tr.havelsan.ueransim.utils.Logging;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.bits.Bit10;

import java.util.UUID;

public class GnbUeManagement {

    public static void createUeContext(GnbSimContext ctx, UUID ueId) {
        Debugging.assertThread(ctx);

        var gnbUeCtx = new GnbUeContext();
        gnbUeCtx.ranUeNgapId = ++ctx.ueNgapIdCounter;
        gnbUeCtx.amfUeNgapId = null;

        ctx.ueContexts.put(ueId, gnbUeCtx);
        selectAmfForUe(ctx, gnbUeCtx);
    }

    public static UUID findUe(GnbSimContext ctx, long ranUeNgapId) {
        Debugging.assertThread(ctx);

        // todo: make O(1)
        for (var entry : ctx.ueContexts.entrySet()) {
            if (entry.getValue().ranUeNgapId == ranUeNgapId) {
                return entry.getKey();
            }
        }
        return null;
    }

    private static void selectAmfForUe(GnbSimContext ctx, GnbUeContext ueCtx) {
        Debugging.assertThread(ctx);

        // todo: always first configured AMF is selected for now
        ueCtx.associatedAmf = ctx.config.amfConfigs[0].guami;
    }

    public static Guami selectNewAmfForReAllocation(GnbSimContext ctx, Guami initiatedAmf, Bit10 amfSetId) {
        Debugging.assertThread(ctx);

        Logging.funcIn("Handling: Select AMF from AMFSetId");
        Logging.debug(Tag.VALUE, "AMFSetId: %s", amfSetId);

        Guami res = null;

        // Filter the AMFs with MCC-MNC-RegionId-SetId
        var amfs = Utils.streamToList(ctx.amfContexts.values().stream().filter(gnbAmfContext ->
                gnbAmfContext.guami.mcc.equals(initiatedAmf.mcc)
                        && gnbAmfContext.guami.mnc.equals(initiatedAmf.mnc)
                        && gnbAmfContext.guami.amfRegionId.equals(initiatedAmf.amfRegionId)
                        && gnbAmfContext.guami.amfSetId.equals(amfSetId)));

        // If set id is the same, select a different AMF from initiated AMF
        if (amfSetId.equals(initiatedAmf.amfSetId)) {
            amfs = Utils.streamToList(amfs.stream().filter(gnbAmfContext ->
                    !gnbAmfContext.guami.amfPointer.equals(initiatedAmf.amfPointer)));
        }

        // Take the first AMF satisfying above conditions
        if (!amfs.isEmpty()) {
            res = amfs.get(0).guami;
        }

        Logging.funcOut();
        return res;
    }
}
