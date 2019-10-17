import { create } from 'zustand'
import { Classes } from '@blueprintjs/core'
import { Constants } from './constants'
import { SocketClient } from './socketClient'

/////////////////////////////// THEME STORE ///////////////////////////////

function updateBodyForTheme(isDark: boolean) {
  const body = document.getElementsByTagName('body')[0]
  if (isDark) {
    body.setAttribute('class', Classes.DARK)
    body.style.backgroundColor = Constants.COLOR_DARK_BACKGROUND
  } else {
    body.setAttribute('class', '')
    body.style.backgroundColor = Constants.COLOR_LIGHT_BACKGROUND
  }
}

export const [useThemeStore] = create(set => ({
  isDark: false,
  toggleTheme: () => set(state => {
    const isDark = !state.isDark
    updateBodyForTheme(isDark)
    return { isDark: isDark }
  }),
}))

/////////////////////////////// APP STORE ///////////////////////////////

export const [useAppStore] = create(set => ({
  appName: 'UE-RAN-SIM',
}))

/////////////////////////////// CONSOLE STORE ///////////////////////////////

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
  toggleOpen: () => set(state => ({
    isOpen: !state.isOpen,
  })),
  toggleAutoScroll: () => set(state => {
    const autoScroll = !state.autoScroll
    if (autoScroll) scrollConsoleToBottom()
    return { autoScroll: autoScroll }
  }),
  clear: () => set(state => ({
    logs: [],
  })),
  appendText: (text: string, className: string) => set(state => {
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


/////////////////////////////// CONTENT STORE ///////////////////////////////

export enum ContentType {
  FLOW_SELECTION = 1,
  FLOW_ACTION = 2
}

export const [useContentStore] = create(set => ({
  contentType: ContentType.FLOW_SELECTION,
  setContent: (contentType: ContentType) => set(state => ({
    contentType: contentType,
  })),
}))


/////////////////////////////// CONTENT STORE ///////////////////////////////

export const [useSocketStore] = create(set => ({
  socketClient: new SocketClient()
}))