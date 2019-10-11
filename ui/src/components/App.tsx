import * as React from 'react'

import { Navigation } from './Navigation'
import { Console } from './Console'
import { Constants } from './Constants'
import { Main } from './Main'
import { Classes } from '@blueprintjs/core'

export class App extends React.Component<any, any> {
  public static isDark: boolean = true
  public static instance: App

  constructor(props: any) {
    super(props)
    App.instance = this
  }

  static setDark(isDark: boolean) {
    App.isDark = isDark
    const body = document.getElementsByTagName('body')[0]
    Console.setDark(isDark)
    if (isDark) {
      body.setAttribute('class', Classes.DARK)
      body.style.backgroundColor = Constants.COLOR_DARK_BACKGROUND
    } else {
      body.setAttribute('class', '')
      body.style.backgroundColor = Constants.COLOR_LIGHT_BACKGROUND
    }
  }

  render() {
    App.setDark(true)

    return (
      <div>
        <Navigation />
        <Main />
        <Console />
      </div>
    )
  }
}
