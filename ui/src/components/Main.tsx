import * as React from 'react'
import { H1, Button, Classes, Card } from '@blueprintjs/core'
import { Console } from './Console'
import { FlowSelector, IFlow } from './FlowSelector'
import { Constants } from './Constants'
import { Navigation } from './Navigation'

export interface IMessage {
  cmd: string
  args: object
}

export class Main extends React.Component<any, any> {
  public static instance: Main
  private ws: WebSocket | null = null

  private flowSelector: FlowSelector | null = null

  constructor(props: any) {
    super(props)
    Main.instance = this
  }

  public render() {
    return (
      <div style={{ margin: '8px' }}>
        <FlowSelector ref={r => (this.flowSelector = r)} />
      </div>
    )
  }

  public componentDidMount() {
    this.setupWebSocket()
  }

  private setupWebSocket() {
    this.ws = new WebSocket('ws://localhost:5002')
    const _this = this
    this.ws.onmessage = function(e: MessageEvent) {
      _this.onMessage(e)
    }
    this.ws.onopen = function(e: Event) {
      _this.onOpen(e)
    }
    this.ws.onclose = function(e: CloseEvent) {
      _this.onClose(e)
    }
    this.ws.onerror = function(e: Event) {
      _this.onError(e)
    }
  }

  private onMessage(e: MessageEvent) {
    if (e.data == null) return
    if (!(typeof e.data === 'string' || e.data instanceof String)) return

    const message: any = JSON.parse(e.data.toString())
    const type: any = message['type']
    const data: any = message['data']

    if (type == null) {
      Console.warn('null message type: ' + type, 'Response')
      return
    }

    switch (type) {
      case 'errorResponse':
        Console.error(data.toString(), 'Error Response')
        return
      case 'allFlows':
        Console.success(
          'flow names retrieved (total ' + data.length + ')',
          'Response'
        )
        if (this.flowSelector != null) {
          const flowItems: IFlow[] = []
          for (let i = 0; i < data.length; i = i + 1) {
            flowItems.push({
              title: data[i],
            })
          }
          this.flowSelector.setState({
            loaded: true,
            selected: null,
            items: flowItems,
          })
        }
        return
      default:
        Console.warn('unrecognized message type: ' + type, 'Response')
        return
    }
  }

  private onOpen(e: Event) {
    Console.success('connection established', 'WebSocket')
    if (this.flowSelector != null) {
      this.flowSelector.startLoading()
    }
  }

  private onClose(e: CloseEvent) {
    Console.success('connection closed', 'WebSocket')
  }

  private onError(e: Event) {
    Console.error('error occured', 'WebSocket')
  }

  public static sendMessage(message: IMessage) {
    Console.log(`sending message of type '${message.cmd}'.`, 'WebSocket')
    const json = JSON.stringify(message)

    if (Main.instance.ws == null) {
      Console.error('Main.instance.ws is null', 'WebSocket')
      return
    }

    Main.instance.ws.send(json)
  }
}
