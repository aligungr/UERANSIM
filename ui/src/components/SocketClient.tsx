import { Console } from './Console'

export class SocketClient {
  private ws: WebSocket

  constructor() {
    const _this = this

    this.ws = new WebSocket('ws://localhost:5002')
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
    Console.log(JSON.stringify(e), 'WebSocket')
  }

  private onOpen(e: Event) {
    Console.success('connection established', 'WebSocket')
  }

  private onClose(e: CloseEvent) {
    Console.success('connection closed', 'WebSocket')
  }

  private onError(e: Event) {
    Console.error('error occured', 'WebSocket')
  }

  send(data: string) {
    this.ws.send(data)
  }
}
