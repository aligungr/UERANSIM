package tr.havelsan.ueransim.flowtesting.inputs;

import tr.havelsan.ueransim.nas.impl.ies.IE5gTmsiMobileIdentity;
import tr.havelsan.ueransim.ngap2.UserLocationInformationNr;


public class ServiceRequestFlowInput {

  public final long ranUeNgapId;
  public final UserLocationInformationNr userLocationInformationNr;
  public final IE5gTmsiMobileIdentity tmsi;

  public ServiceRequestFlowInput(long ranUeNgapId,
      UserLocationInformationNr userLocationInformationNr,
      IE5gTmsiMobileIdentity tmsi) {
    this.ranUeNgapId = ranUeNgapId;
    this.userLocationInformationNr = userLocationInformationNr;
    this.tmsi = tmsi;
  }
}
