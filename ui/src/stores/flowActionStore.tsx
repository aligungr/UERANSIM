import { create } from 'zustand'

export const [useFlowActionStore] = create(set => ({
  machineInfo: {} as any,
  setMachineInfo: (machineInfo: any) =>
    set(state => ({
      machineInfo: machineInfo,
    })),
}))


