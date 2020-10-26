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
 */

package tr.havelsan.ueransim.app.common.configs;

import tr.havelsan.ueransim.app.gnb.utils.SupportedTA;
import tr.havelsan.ueransim.nas.impl.values.VPlmn;
import tr.havelsan.ueransim.utils.bits.BitString;

public class GnbConfig {
    public final int gnbId;
    public final int tac;
    public final BitString nci;
    public final VPlmn gnbPlmn;
    public final GnbAmfConfig[] amfConfigs;
    public final SupportedTA[] supportedTAs;
    public final boolean ignoreStreamIds;
    public final String host;
    public final int gtpPort;
    public final int tunPort;

    public GnbConfig(int gnbId, int tac, String nci, VPlmn gnbPlmn, GnbAmfConfig[] amfConfigs, SupportedTA[] supportedTAs, boolean ignoreStreamIds, String host, int gtpPort, int tunPort) {
        this.gnbId = gnbId;
        this.tac = tac;
        this.nci = BitString.fromBits(nci);
        this.gnbPlmn = gnbPlmn;
        this.amfConfigs = amfConfigs;
        this.supportedTAs = supportedTAs;
        this.ignoreStreamIds = ignoreStreamIds;
        this.host = host;
        this.gtpPort = gtpPort;
        this.tunPort = tunPort;
    }
}
