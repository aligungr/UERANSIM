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

package tr.havelsan.ueransim.ngap3.pdu_descriptions;

public class NGAP_NGAP_PDU {

    public static int PRESENT_NOTHING = 0;
    public static int PRESENT_INITIATING_MESSAGE = 1;
    public static int PRESENT_SUCCESSFUL_OUTCOME = 2;
    public static int PRESENT_UNSUCCESSFUL_OUTCOME = 3;

    public int present;

    public NGAP_initiatingMessage initiatingMessage;
    public NGAP_successfulOutcome successfulOutcome;
    public NGAP_unsuccessfulOutcome unsuccessfulOutcome;

    public NGAP_NGAP_PDU() {
        this.present = PRESENT_NOTHING;
        this.initiatingMessage = null;
        this.successfulOutcome = null;
        this.unsuccessfulOutcome = null;
    }

    public NGAP_NGAP_PDU(NGAP_initiatingMessage initiatingMessage) {
        this.present = PRESENT_INITIATING_MESSAGE;
        this.initiatingMessage = initiatingMessage;
        this.successfulOutcome = null;
        this.unsuccessfulOutcome = null;
    }

    public NGAP_NGAP_PDU(NGAP_successfulOutcome successfulOutcome) {
        this.present = PRESENT_SUCCESSFUL_OUTCOME;
        this.initiatingMessage = null;
        this.successfulOutcome = successfulOutcome;
        this.unsuccessfulOutcome = null;
    }

    public NGAP_NGAP_PDU(NGAP_unsuccessfulOutcome unsuccessfulOutcome) {
        this.present = PRESENT_UNSUCCESSFUL_OUTCOME;
        this.initiatingMessage = null;
        this.successfulOutcome = null;
        this.unsuccessfulOutcome = unsuccessfulOutcome;
    }
}
