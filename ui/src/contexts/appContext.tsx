import * as React from 'react'

export class AppInfo {
  appName: string = ''
}

export const AppContext = React.createContext(new AppInfo())
