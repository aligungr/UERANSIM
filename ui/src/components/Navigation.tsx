import * as React from 'react'
import {
  Alignment,
  AnchorButton,
  Classes,
  Navbar,
  NavbarGroup,
  NavbarHeading,
  NavbarDivider,
} from '@blueprintjs/core'
import { Console } from './Console'
import { App } from './App'

interface INavigationState {
  isConsoleOpen: boolean
  isDark: boolean
}

export class Navigation extends React.Component<any, INavigationState> {
  constructor(props: any) {
    super(props)
    this.state = {
      isConsoleOpen: true,
      isDark: true,
    }
  }

  public render() {
    return (
      <Navbar>
        <NavbarGroup align={Alignment.LEFT}>
          <NavbarHeading>UERANSIM</NavbarHeading>
          <NavbarDivider />
          <AnchorButton
            // text={(this.state.isConsoleOpen ? "Hide" : "Show") + " Console"}
            minimal={true}
            rightIcon="console"
            onClick={() => this.handleConsoleClick()}
            // active={this.state.isConsoleOpen}
          />
          <AnchorButton
            // text={(this.state.isConsoleOpen ? "Hide" : "Show") + " Console"}
            minimal={true}
            rightIcon={this.state.isDark ? 'flash' : 'moon'}
            onClick={() => this.handleThemeClicked()}
            // active={this.state.isConsoleOpen}
          />
        </NavbarGroup>
      </Navbar>
    )
  }

  handleConsoleClick() {
    if (this.state.isConsoleOpen) {
      this.setState({ isConsoleOpen: false })
      Console.hide()
    } else {
      this.setState({ isConsoleOpen: true })
      Console.show()
    }
  }

  handleThemeClicked() {
    App.setDark(!App.isDark)
    this.setState({ isDark: App.isDark })
  }
}
