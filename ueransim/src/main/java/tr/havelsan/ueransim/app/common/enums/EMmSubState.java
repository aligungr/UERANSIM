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

package tr.havelsan.ueransim.app.common.enums;

public enum EMmSubState {
    MM_NULL__NA,

    MM_DEREGISTERED__NA,
    MM_DEREGISTERED__NORMAL_SERVICE,
    MM_DEREGISTERED__LIMITED_SERVICE,
    MM_DEREGISTERED__ATTEMPTING_REGISTRATION,
    MM_DEREGISTERED__PLMN_SEARCH,
    MM_DEREGISTERED__NO_SUPI,
    MM_DEREGISTERED__NO_CELL_AVAILABLE,
    MM_DEREGISTERED__ECALL_INACTIVE,
    MM_DEREGISTERED__INITIAL_REGISTRATION_NEEDED,

    MM_REGISTERED_INITIATED__NA,

    MM_REGISTERED__NA,
    MM_REGISTERED__NORMAL_SERVICE,
    MM_REGISTERED__NON_ALLOWED_SERVICE,
    MM_REGISTERED__ATTEMPTING_REGISTRATION_UPDATE,
    MM_REGISTERED__LIMITED_SERVICE,
    MM_REGISTERED__PLMN_SEARCH,
    MM_REGISTERED__NO_CELL_AVAILABLE,
    MM_REGISTERED__UPDATE_NEEDED,

    MM_DEREGISTERED_INITIATED__NA,

    MM_SERVICE_REQUEST_INITIATED__NA;
}
