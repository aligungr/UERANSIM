import * as React from 'react'

import { Hello } from './Hello'
import { Navigation } from './Navigation'
import { Console } from './Console'

export class App extends React.Component<any, any> {
  public static isDark: boolean = true

  constructor(props: any) {
    super(props)
  }

  static setDark(isDark: boolean) {
    App.isDark = isDark
    const body = document.getElementsByTagName('body')[0]
    if (isDark) {
      body.setAttribute('class', 'bp3-dark')
      body.style.backgroundColor = '#30404d'
    } else {
      body.setAttribute('class', '')
      body.style.backgroundColor = '#ffffff'
    }
  }

  render() {
    App.setDark(true)

    return (
      <div>
        <Navigation />
        <Hello />
        <Console />
      </div>
    )
  }
}
