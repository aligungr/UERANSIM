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

package tr.havelsan.ueransim.app.common;

import tr.havelsan.ueransim.nas.impl.enums.EPduSessionIdentity;
import tr.havelsan.ueransim.nas.impl.ies.IEQoSFlowDescriptions;
import tr.havelsan.ueransim.nas.impl.ies.IEQoSRules;
import tr.havelsan.ueransim.nas.impl.ies.IESessionAmbr;

public class PduSession {

    public static final int MIN_ID = 1;
    public static final int MAX_ID = 15;

    public static final PduSession RELEASED = new PduSession(EPduSessionIdentity.NO_VAL);

    public final EPduSessionIdentity id;
    public boolean isEstablished;
    public IEQoSRules authorizedQoSRules;
    public IESessionAmbr sessionAmbr;
    public IEQoSFlowDescriptions authorizedQoSFlowDescriptions;

    public PduSession(EPduSessionIdentity id) {
        this.id = id;
    }
}
