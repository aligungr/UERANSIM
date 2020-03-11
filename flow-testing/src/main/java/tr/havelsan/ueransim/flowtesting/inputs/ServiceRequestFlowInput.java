package tr.havelsan.ueransim.flowtesting.inputs;

import tr.havelsan.ueransim.sim.ue.UserLocationInformationNr;

public class ServiceRequestFlowInput {

  public final long ranUeNgapId;
  public final UserLocationInformationNr userLocationInformationNr;
  public final String amfSetId;
  public final String amfPointer;
  public final String fiveg_tmsi;

  public ServiceRequestFlowInput(long ranUeNgapId,
      UserLocationInformationNr userLocationInformationNr, String amfSetId,
      String amfPointer, String fiveg_tmsi) {
    this.ranUeNgapId = ranUeNgapId;
    this.userLocationInformationNr = userLocationInformationNr;
    this.amfSetId = amfSetId;
    this.amfPointer = amfPointer;
    this.fiveg_tmsi = fiveg_tmsi;
  }
}
