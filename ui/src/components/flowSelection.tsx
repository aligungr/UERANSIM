import * as React from 'react'
import { ItemPredicate, ItemRenderer, Select } from '@blueprintjs/select'


export interface IMachineInfo {
  machineName: string
  starter: string
  states: string[]
}

export class FlowSelection extends React.Component<any, IFlowSelectorState> {

  private selectClicked() {
    //Broadcast.sendSocketMessage('setupFlow', { arg0: flowTitle })
  }
}
