import {
  Alignment,
  AnchorButton,
  Navbar,
  NavbarDivider,
  NavbarGroup,
  NavbarHeading,
  Tooltip,
} from '@blueprintjs/core'
import * as React from 'react'
import { AppInfo, AppContext } from '../contexts/appContext'
import { ThemeInfo, ThemeContext } from '../contexts/themeContext'
import { ConsoleInfo, ConsoleContext } from '../contexts/consoleContext'

export function Navigation(props: any) {
  return (
    <AppContext.Consumer>
      {(appInfo: AppInfo) => (
        <ThemeContext.Consumer>
          {(themeInfo: ThemeInfo) => (
            <ConsoleContext.Consumer>
              {(consoleInfo: ConsoleInfo) =>
                renderWithContext(appInfo, themeInfo, consoleInfo)
              }
            </ConsoleContext.Consumer>
          )}
        </ThemeContext.Consumer>
      )}
    </AppContext.Consumer>
  )
}

function renderWithContext(
  appInfo: AppInfo,
  themeInfo: ThemeInfo,
  consoleInfo: ConsoleInfo
) {
  return (
    <Navbar>
      <NavbarGroup align={Alignment.LEFT}>
        <NavbarHeading>{appInfo.appName}</NavbarHeading>
        <NavbarDivider />
        <Tooltip content={`${consoleInfo.isOpen ? 'Hide' : 'Show'} Console`}>
          <AnchorButton
            minimal={true}
            rightIcon="console"
            onClick={() => {
              consoleInfo.toggleOpen()
            }}
            // active={this.state.isConsoleOpen}
          />
        </Tooltip>
        <Tooltip
          content={`Switch to ${themeInfo.isDark ? 'Light' : 'Dark'} Theme`}
        >
          <AnchorButton
            minimal={true}
            rightIcon={themeInfo.isDark ? 'flash' : 'moon'}
            onClick={() => themeInfo.toggleTheme()}
          />
        </Tooltip>
      </NavbarGroup>
    </Navbar>
  )
}
