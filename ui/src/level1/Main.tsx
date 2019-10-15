import * as React from 'react'
import { Console } from './Console'
import { FlowSelection } from '../level2/FlowSelection'
import { FlowAction } from '../level2/FlowAction'
import { BaseComponent } from '../basis/BaseComponent'
import { Broadcast } from '../basis/Broadcast'

export enum MainContent {
  FLOW_SELECTION = 1,
  FLOW_ACTION = 2,
}

interface IMainState {
  mainContent: MainContent
}

export class Main extends BaseComponent<any, IMainState> {
  public static instance: Main
  private ws: WebSocket | null = null

  constructor(props: any) {
    super(props)
    Main.instance = this
    this.state = { mainContent: Broadcast.getMainContent() }
  }

  public componentDidMount() {
    super.componentDidMount()
    this.setupWebSocket()
  }

  private setupWebSocket() {
    this.ws = new WebSocket('ws://localhost:5002')
    this.ws.onopen = function(e: Event) {
      Console.success('connection established', 'WebSocket')
      Broadcast.onSocketConnected(e)
    }
    this.ws.onclose = function(e: CloseEvent) {
      Console.success('connection closed', 'WebSocket')
      Broadcast.onSocketClosed(e)
    }
    this.ws.onerror = function(e: Event) {
      Console.error('error occured', 'WebSocket')
      Broadcast.onSocketError(e)
    }
    this.ws.onmessage = function(e: MessageEvent) {
      if (e.data == null) return
      if (!(typeof e.data === 'string' || e.data instanceof String)) return

      const message: any = JSON.parse(e.data.toString())
      const type: any = message['type']
      const data: any = message['data']

      if (type == null) {
        Console.warn('null message type: ' + type, 'Response')
        return
      }

      if (type === 'errorResponse') {
        Console.error(data.toString(), 'Error Response')
        return
      }

      Console.log('message received: ' + type, 'Response')
      Broadcast.onSocketMessage(type, data)
    }
  }

  public getWebSocket() {
    return this.ws
  }

  public onMainContentChanged(mainContent: MainContent) {
    this.setState({ mainContent: mainContent })
  }

  public render() {
    let content

    switch (this.state.mainContent) {
      case MainContent.FLOW_SELECTION:
        content = <FlowSelection />
        break
      case MainContent.FLOW_ACTION:
        content = <FlowAction />
    }

    return <div style={{ margin: '8px' }}>{content}</div>
  }
}
