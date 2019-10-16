import * as React from 'react'

export class ConsoleInfo {
  isOpen: boolean = false
  autoScroll: boolean = false
  toggleOpen: () => void = () => { }
  toggleAutoScroll: () => void = () => { }
}

export const ConsoleContext = React.createContext(new ConsoleInfo())
