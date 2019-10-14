import * as React from 'react'
import { Console } from '../level1/Console'
import { Classes } from '@blueprintjs/core'
import { Constants } from '../classes/Constants'
import { Navigation } from '../level1/Navigation'
import { Main } from '../level1/Main'
import { BaseComponent } from '../basis/BaseComponent'
import { Broadcast } from '../basis/Broadcast'

export interface IApplicationState {
  isDark: boolean
}

export class Application extends BaseComponent<any, IApplicationState> {
  private static instance: Application

  public constructor(props: any) {
    super(props)
    this.state = { isDark: Broadcast.isDark() }
    Application.instance = this
  }

  public onThemeChanged(isDark: boolean): void {
    const body = document.getElementsByTagName('body')[0]
    if (isDark) {
      body.setAttribute('class', Classes.DARK)
      body.style.backgroundColor = Constants.COLOR_DARK_BACKGROUND
    } else {
      body.setAttribute('class', '')
      body.style.backgroundColor = Constants.COLOR_LIGHT_BACKGROUND
    }
    this.setState({ isDark: isDark })
  }

  componentDidMount(): void {
    super.componentDidMount()
    Broadcast.setDark(true)
  }

  public render() {
    return (
      <div>
        <Navigation />
        <Main />
        <Console />
      </div>
    )
  }
}
