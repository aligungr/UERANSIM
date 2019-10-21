import { create } from 'zustand'
import { LogEntry, LogType } from '../components/logger'

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