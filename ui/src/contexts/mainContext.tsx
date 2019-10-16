import * as React from 'react'

export enum MainContent {
  FLOW_SELECTION = 1,
  FLOW_ACTION = 2,
}

export class MainInfo {
  mainContent: MainContent = MainContent.FLOW_SELECTION
  setContent: (mainContent: MainContent) => void = () => { }
  sendSocketMessage: (message: string) => void = () => { }
}

export const MainContext = React.createContext(new MainInfo())
