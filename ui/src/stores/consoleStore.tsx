import { create } from 'zustand'
import { LogEntry, LogType } from '../components/logger'

let consoleLogId = 1

function scrollConsoleToBottom() {
  const element = document.getElementById('bp-console-content')
  if (element == null) throw new Error()
  element.scrollTop = element.scrollHeight
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
  append: (text: string, logType: LogType) =>
    set(state => {
      consoleLogId++
      const entry: LogEntry = {
        type: logType,
        entry: text,
        id: consoleLogId,
      }

      const entries = state.logs
      entries.push(entry)
      if (state.autoScroll) {
        scrollConsoleToBottom()
      }
      return { logs: entries }
    }),
}))
