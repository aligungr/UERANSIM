import { create } from 'zustand'

export const [useAppStore] = create(set => ({
  appName: 'UE-RAN-SIM',
}))
