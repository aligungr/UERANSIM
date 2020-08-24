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

package tr.havelsan.ueransim.app.core;

import tr.havelsan.ueransim.app.api.sys.SimulationContext;
import tr.havelsan.ueransim.app.events.gnb.GnbEvent;
import tr.havelsan.ueransim.app.structs.GnbAmfContext;
import tr.havelsan.ueransim.app.structs.GnbConfig;
import tr.havelsan.ueransim.app.structs.GnbUeContext;
import tr.havelsan.ueransim.app.structs.Guami;

import java.util.HashMap;
import java.util.UUID;

public class GnbSimContext extends BaseSimContext<GnbEvent> {
    public GnbConfig config;

    public HashMap<Guami, GnbAmfContext> amfContexts;

    public HashMap<UUID, GnbUeContext> ueContexts;
    public long ueNgapIdCounter;

    public GnbSimContext(SimulationContext simCtx) {
        super(simCtx);
        this.amfContexts = new HashMap<>();
        this.ueContexts = new HashMap<>();
    }
}
