import * as React from 'react'
import { H1, Button, Classes, Card } from '@blueprintjs/core'
import { Console } from './Console'
import { FlowSelector } from './FlowSelector'
import { SocketClient } from './SocketClient'
import { Constants } from './Constants'
import { Navigation } from './Navigation'

export class Main extends React.Component<any, any> {
  public static isDark: boolean = true
  public static instance: Main
  private static ws: SocketClient

  constructor(props: any) {
    super(props)
    Main.instance = this;
  }

  render() {
    return (
      <div style={{ margin: '8px' }}>
        <Button
          className={Classes.DARK}
          text={"btn"}
          onClick={() => {
            if (console != null) {
              Console.log('slm, ' + Math.random() + '')
            }
          }}
        />
        <FlowSelector />
      </div>
    )
  }

  componentDidMount(): void {
    Main.ws = new SocketClient()
  }
}
