import { create } from 'zustand'

let consoleLogId = 1

function scrollConsoleToBottom() {
  const element = document.getElementById('bp-console-content')
  if (element == null) throw new Error()
  element.scrollTop = element.scrollHeight
}

export class ConsoleLogEntry {
  text: string = ''
  className: string = ''
  entryId: number = 0
}

export const [useConsoleStore] = create(set => ({
  isOpen: true,
  autoScroll: true,
  logs: [],
  toggleOpen: () =>
    set(state => ({
      isOpen: !state.isOpen,
    })),
  toggleAutoScroll: () =>
    set(state => {
      const autoScroll = !state.autoScroll
      if (autoScroll) scrollConsoleToBottom()
      return { autoScroll: autoScroll }
    }),
  clear: () =>
    set(state => ({
      logs: [],
    })),
  appendText: (text: string, className: string) =>
    set(state => {
      consoleLogId++
      const entry = {
        className,
        text,
        entryId: consoleLogId,
      }

      const entries = state.logs
      entries.push(entry)
      if (state.autoScroll) {
        scrollConsoleToBottom()
      }
      return { logs: entries }
    }),
}))
