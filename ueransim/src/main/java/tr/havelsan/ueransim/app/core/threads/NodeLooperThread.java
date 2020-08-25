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

package tr.havelsan.ueransim.app.core.threads;

import tr.havelsan.ueransim.app.Program;
import tr.havelsan.ueransim.app.core.BaseSimContext;
import tr.havelsan.ueransim.utils.Logging;
import tr.havelsan.ueransim.utils.Tag;

import java.util.function.Consumer;

public final class NodeLooperThread<T extends BaseSimContext<?>> extends BaseThread {

    private final T simContext;
    private final Consumer<T> looper;

    public NodeLooperThread(T simContext, Consumer<T> looper) {
        this.simContext = simContext;
        this.looper = looper;
    }

    @Override
    public void run() {
        Logging.debug(Tag.SYSTEM, "%s has started: %s", simContext.getClass().getSimpleName(), simContext.ctxId);
        while (true) {
            looper.accept(simContext);
            while (simContext.hasEvent()) {
                looper.accept(simContext);
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Program.fail(e);
            }
        }
    }
}
