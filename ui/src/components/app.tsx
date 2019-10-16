import { Classes } from '@blueprintjs/core'
import * as React from 'react'
import { AppContext, AppInfo } from '../contexts/appContext'
import { ConsoleContext, ConsoleInfo } from '../contexts/consoleContext'
import { ThemeContext, ThemeInfo } from '../contexts/themeContext'
import { Constants } from '../basis/constants'
import { Content } from './content'

export interface AppState {
  appInfo: AppInfo
  consoleInfo: ConsoleInfo
  themeInfo: ThemeInfo
}

export class App extends React.Component<any, AppState> {
  public constructor(props: any) {
    super(props)

    this.state = {
      appInfo: {
        appName: 'UE-RAN-SIM',
      },
      consoleInfo: {
        isOpen: true,
        autoScroll: true,
        toggleOpen: () => {
          this.toggleConsoleOpen()
        },
        toggleAutoScroll: () => {
          this.toggleConsoleAutoScroll()
        },
      },
      themeInfo: {
        isDark: true,
        toggleTheme: () => {
          this.toggleTheme()
        },
      },
    }
  }

  private toggleTheme() {
    this.setState({
      themeInfo: {
        isDark: !this.state.themeInfo.isDark,
        toggleTheme: this.state.themeInfo.toggleTheme,
      },
    })
    this.updateBodyForTheme()
  }

  private updateBodyForTheme() {
    const body = document.getElementsByTagName('body')[0]
    if (this.state.themeInfo.isDark) {
      body.setAttribute('class', Classes.DARK)
      body.style.backgroundColor = Constants.COLOR_DARK_BACKGROUND
    } else {
      body.setAttribute('class', '')
      body.style.backgroundColor = Constants.COLOR_LIGHT_BACKGROUND
    }
  }

  private toggleConsoleOpen() {
    this.setState({
      consoleInfo: {
        isOpen: !this.state.consoleInfo.isOpen,
        autoScroll: this.state.consoleInfo.autoScroll,
        toggleOpen: this.state.consoleInfo.toggleOpen,
        toggleAutoScroll: this.state.consoleInfo.toggleAutoScroll,
      },
    })
  }

  private toggleConsoleAutoScroll() {
    this.setState({
      consoleInfo: {
        isOpen: this.state.consoleInfo.isOpen,
        autoScroll: !this.state.consoleInfo.autoScroll,
        toggleOpen: this.state.consoleInfo.toggleOpen,
        toggleAutoScroll: this.state.consoleInfo.toggleAutoScroll,
      },
    })
  }

  public render() {
    this.updateBodyForTheme()

    return (
      <AppContext.Provider value={this.state.appInfo}>
        <ThemeContext.Provider value={this.state.themeInfo}>
          <ConsoleContext.Provider value={this.state.consoleInfo}>
            <Content />
          </ConsoleContext.Provider>
        </ThemeContext.Provider>
      </AppContext.Provider>
    )
  }
}
