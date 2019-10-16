import * as React from 'react'
import { MainContent, MainContext, MainInfo } from '../contexts/mainContext'
import { konsol } from '../basis/konsol'
import { FlowSelection } from './flowSelection'

export interface MainState {
  mainInfo: MainInfo,
}

export class Main extends React.Component<any, MainState> {
  private webSocket: WebSocket | null = null

  constructor(props: any) {
    super(props)

    this.state = {
      mainInfo: {
        mainContent: MainContent.FLOW_SELECTION,
        setContent: mainContent => {
          this.setContent(mainContent)
        },
        sendSocketMessage: message => {
          this.sendSocketMessage(message)
        },
      },
    }
  }

  private setContent(mainContent: MainContent) {
    this.setState({
      mainInfo: {
        mainContent: mainContent,
        sendSocketMessage: this.state.mainInfo.sendSocketMessage,
        setContent: this.state.mainInfo.setContent,
      },
    })
  }

  private sendSocketMessage(message: string) {
    const webSocket = this.webSocket
    if (webSocket == null) {
      konsol.error('web socket is not ready yet')
      return
    }
    webSocket.send(message)
  }

  public render() {
    return (
      <MainContext.Provider value={this.state.mainInfo}>
        <MainContext.Consumer>
          {mainInfo => this.renderWithContext(mainInfo)}
        </MainContext.Consumer>
      </MainContext.Provider>
    )
  }

  private renderWithContext(mainInfo: MainInfo) {
    if (mainInfo.mainContent === MainContent.FLOW_SELECTION) {
      return (
        <FlowSelection/>
      )
    } else {
      return (
        <div>
          flow action
        </div>
      )
    }
  }

  public componentDidMount(): void {
    this.setupWebSocket()
  }

  private setupWebSocket() {
    const ws = new WebSocket('ws://localhost:5002')
    ws.onopen = function(e: Event) {
      konsol.success('connection established', 'WebSocket')
    }
    ws.onclose = function(e: CloseEvent) {
      konsol.success('connection closed', 'WebSocket')
    }
    ws.onerror = function(e: Event) {
      konsol.error('error occured', 'WebSocket')
    }
    ws.onmessage = function(e: MessageEvent) {
      if (e.data == null) return
      if (!(typeof e.data === 'string' || e.data instanceof String)) return

      const message: any = JSON.parse(e.data.toString())
      const type: any = message['type']
      const data: any = message['data']

      if (type == null) {
        konsol.warn('null message type: ' + type, 'Response')
        return
      }
      if (type === 'errorResponse') {
        konsol.error(data.toString(), 'Error Response')
        return
      }
      konsol.log('message received: ' + type, 'Response')
      // Broadcast.onSocketMessage(type, data)
    }
    this.webSocket = ws
  }
}

