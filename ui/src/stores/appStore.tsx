import { create } from 'zustand'

export const [useAppStore] = create<AppStore>(set => ({
  appName: 'UE-RAN-SIM',
  newFlowLoading: false,
  setNewFlowLoading: (status: boolean) => set({ newFlowLoading: status }),
}))

type AppStore = {
  appName: string
  newFlowLoading: boolean
  setNewFlowLoading: (status: boolean) => void
}
