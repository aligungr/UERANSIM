import * as React from 'react'
import { BaseComponent } from '../basis/BaseComponent'
import { Broadcast } from '../basis/Broadcast'
import { IMachineInfo } from '../classes/IMachineInfo'
import { Console } from '../basis/Console'
import { Divider, Pre } from '@blueprintjs/core'
import { StateButtons } from '../level3/StateButtons'

interface IFlowActionState {
  machineInfo: IMachineInfo
}

export class FlowAction extends BaseComponent<any, IFlowActionState> {
  constructor(props: any) {
    super(props)

    const machineInfo = Broadcast.getMachineInfo()
    if (machineInfo == null) {
      throw new Error('machineInfo was null')
    }
    this.state = {
      machineInfo: machineInfo,
    }
  }

  public onMachineInfo(machineInfo: IMachineInfo) {
    this.setState({ machineInfo: machineInfo })
    Console.warn('onMachineInfo called of FlowAction')
  }

  public render() {
    return (
      <div>
        <h3 className="bp3-heading">{this.state.machineInfo.machineName}</h3>
        <StateButtons />
      </div>
    )
  }
}
