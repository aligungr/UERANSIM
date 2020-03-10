package tr.havelsan.ueransim.flowtesting.inputs;

import tr.havelsan.ueransim.sim.ue.UserLocationInformationNr;

public class ServiceRequestFlowInput {

  public final long ranUeNgapId;
  public final UserLocationInformationNr userLocationInformationNr;

  public ServiceRequestFlowInput(long ranUeNgapId,
      UserLocationInformationNr userLocationInformationNr) {
    this.ranUeNgapId = ranUeNgapId;
    this.userLocationInformationNr = userLocationInformationNr;
  }

}
