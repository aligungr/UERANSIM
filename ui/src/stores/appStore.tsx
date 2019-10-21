import { create } from 'zustand'

export const [useAppStore] = create<AppStore>(set => ({
  appName: 'UE-RAN-SIM',
}))

type AppStore = {
  appName: string
}