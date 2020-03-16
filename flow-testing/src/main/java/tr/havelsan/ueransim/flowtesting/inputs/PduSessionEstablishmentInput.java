package tr.havelsan.ueransim.flowtesting.inputs;

import tr.havelsan.ueransim.ngap2.UserLocationInformationNr;
import tr.havelsan.ueransim.nas.impl.ies.IEDnn;
import tr.havelsan.ueransim.nas.impl.ies.IESNssai;
import tr.havelsan.ueransim.utils.octets.Octet;

public class PduSessionEstablishmentInput {
  public final long ranUeNgapId;
  public final long amfUeNgapId;
  public final Octet pduSessionId;
  public final Octet procedureTransactionId;
  public final IESNssai sNssai;
  public final IEDnn dnn;
  public final UserLocationInformationNr userLocationInformationNr;

  public PduSessionEstablishmentInput(
      UserLocationInformationNr userLocationInformationNr, long ranUeNgapId, long amfUeNgapId,
      Octet pduSessionId, Octet procedureTransactionId,
      IESNssai sNssai, IEDnn dnn) {
    this.userLocationInformationNr = userLocationInformationNr;
    this.ranUeNgapId = ranUeNgapId;
    this.amfUeNgapId = amfUeNgapId;
    this.pduSessionId = pduSessionId;
    this.procedureTransactionId = procedureTransactionId;
    this.sNssai = sNssai;
    this.dnn = dnn;
  }
}
