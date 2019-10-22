import { create } from 'zustand'

export const [useFlowSelectionStore] = create<FlowSelectionStore>(set => ({
  isLoaded: false,
  selected: null,
  buttonEnabled: true,
  items: [],

  setIsLoaded: (loaded: boolean) => set({ isLoaded: loaded }),
  setSelected: (selected: string | null) => set({ selected: selected }),
  setButtonEnabled: (enabled: boolean) => set({ buttonEnabled: enabled }),
  setItems: (items: string[]) => set({ items: items }),
}))

type FlowSelectionStore = {
  isLoaded: boolean,
  selected: string | null,
  buttonEnabled: boolean,
  items: string[],

  setIsLoaded(status: boolean): void,
  setSelected(selected: string | null): void,
  setButtonEnabled(enabled: boolean): void,
  setItems(items: string[]): void
}
