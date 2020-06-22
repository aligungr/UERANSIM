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

package tr.havelsan.ueransim.utils;

import kotlin.Pair;

import java.util.ArrayList;
import java.util.function.Consumer;

public final class Funs {
    private ArrayList<Pair<Fun, Consumer<Exception>>> funs;

    private Funs(Pair<Fun, Consumer<Exception>> fun) {
        this.funs = new ArrayList<>();
        this.funs.add(fun);
    }

    public static Funs run(Fun fun) {
        return run(fun, null);
    }

    public static Funs run(Fun fun, Consumer<Exception> onError) {
        return new Funs(new Pair<>(fun, onError));
    }

    public Funs then(Fun fun) {
        funs.add(new Pair<>(fun, null));
        return this;
    }

    public Funs then(Fun fun, Consumer<Exception> onError) {
        funs.add(new Pair<>(fun, onError));
        return this;
    }

    public void invoke() {
        for (var pair : funs) {
            if (pair.getSecond() == null) {
                pair.getFirst().run();
            } else {
                try {
                    pair.getFirst().run();
                } catch (Exception e) {
                    pair.getSecond().accept(e);
                    return;
                }
            }
        }
    }
}
