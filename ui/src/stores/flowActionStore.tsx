import { create } from 'zustand'

export const [useFlowActionStore] = create<FlowStore>(set => ({
  machineInfo: {},
  loading: false,
  setLoading: (status: boolean) => set({ loading: status }),
  setMachineInfo: (machineInfo: any) =>
    set(state => ({
      machineInfo: machineInfo,
    })),
}))

type FlowStore = {
  machineInfo: any
  loading: boolean

  setMachineInfo(machineInfo: any): void
}
