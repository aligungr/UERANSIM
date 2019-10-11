import * as React from 'react'
import { H1, Button, Classes, Card } from '@blueprintjs/core'
import { Console } from './Console'
import { FlowSelector } from './FlowSelector'
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
    console.log(e.data)
    Console.log(JSON.stringify(e), 'WebSocket')
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
