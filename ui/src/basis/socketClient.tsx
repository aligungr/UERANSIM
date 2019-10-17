import { logger } from '../components/logger'

export interface ISocketListener {
  onOpen: (event: Event) => void,
  onClose: (event: CloseEvent) => void
  onError: (event: Event) => void
  onMessage: (type: string, data: any) => void
}

interface EventEntry {
  eventType: string,
  event: any
}

export class SocketClient {
  private ws: WebSocket | undefined
  private listeners = new Map<string, ISocketListener>()
  private events: EventEntry[]
  private initialized: boolean

  constructor() {
    this.events = []
    this.initialized = false
  }

  public initialize() {
    if (this.initialized) return
    this.initialized = true
    const ws = new WebSocket('ws://localhost:5002')
    ws.onopen = e => {
      this.onOpen(e)
    }
    ws.onclose = e => {
      this.onClose(e)
    }
    ws.onerror = e => {
      this.onError(e)
    }
    ws.onmessage = e => {
      this.onMessage(e)
    }
    this.ws = ws;
  }

  private onOpen(e: Event) {
    logger.success('connection established', 'WebSocket')
    this.events.push({
      eventType: 'onOpen',
      event: e,
    })
    this.listeners.forEach(value => {
      value.onOpen(e)
    })
  }

  private onClose(e: CloseEvent) {
    logger.success('connection closed', 'WebSocket')
    this.events.push({
      eventType: 'onClose',
      event: e,
    })
    this.listeners.forEach(value => {
      value.onClose(e)
    })
  }

  private onError(e: Event) {
    logger.error('error happened', 'WebSocket')
    this.events.push({
      eventType: 'onError',
      event: e,
    })
    this.listeners.forEach(value => {
      value.onError(e)
    })
  }

  private onMessage(e: MessageEvent) {
    if (e.data == null) return
    if (!(typeof e.data === 'string' || e.data instanceof String)) return

    const message: any = JSON.parse(e.data.toString())
    const type: any = message['type']
    const data: any = message['data']

    if (type == null) {
      logger.warning('null message type: ' + type, 'Response')
      return
    }
    if (type === 'errorResponse') {
      logger.error(data.toString(), 'Error Response')
      return
    }
    logger.log('message received: ' + type, 'Response')

    this.events.push({
      eventType: 'onMessage',
      event: {
        type: type,
        data: data,
      },
    })

    this.listeners.forEach(value => {
      value.onMessage(type, data)
    })
  }

  public registerListener(listenerKey: string, socketListener: ISocketListener, receiveOldEvents: boolean = true) {
    if (this.listeners.has(listenerKey)) return
    this.listeners.set(listenerKey, socketListener)

    if (receiveOldEvents) {
      this.events.forEach(value => {
        switch (value.eventType) {
          case 'onOpen':
            socketListener.onOpen(value.event)
            break
          case 'onClose':
            socketListener.onClose(value.event)
            break
          case 'onError':
            socketListener.onError(value.event)
            break
          case 'onMessage':
            socketListener.onMessage(value.event.type, value.event.data)
            break
        }
      })
    }
  }

  public unregisterListener(listenerKey: string) {
    this.listeners.delete(listenerKey)
  }

  public sendMessage(cmd: string, args: object) {
    if (this.ws == null) {
      logger.error('webSocket is not ready')
      return
    }
    this.ws.send(JSON.stringify({
      cmd: cmd,
      args: args,
    }))
  }
}

