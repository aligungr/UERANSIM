import { create } from 'zustand'
import { LogEntry, LogType } from '../components/logger'

function scrollConsoleToBottom() {
  const element = document.getElementById('bp-console-content')
  if (element == null) throw new Error()
  element.scrollTop = element.scrollHeight
}

export const [useLoggerStore] = create<LoggerStore>(set => ({
  isOpen: false,
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
      const entry: LogEntry = {
        type: logType,
        entry: text,
      }

      const entries = state.logs
      entries.push(entry)
      if (state.autoScroll) {
        scrollConsoleToBottom()
      }
      return { logs: entries }
    }),
}))

export type LoggerStore = {
  isOpen: boolean,
  autoScroll: boolean,
  logs: LogEntry[],
  toggleOpen(): void,
  toggleAutoScroll(): void,
  clear(): void,
  append(text: string, logType: LogType): void,
}