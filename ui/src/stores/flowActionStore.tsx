import { create } from 'zustand'

export const [useFlowActionStore] = create<FlowActionStore>(set => ({
  machineInfo: {},
  setMachineInfo: (machineInfo: any) =>
    set(state => ({
      machineInfo: machineInfo,
    })),
}))

type FlowActionStore = {
  machineInfo: any,
  setMachineInfo(machineInfo: any): void
}