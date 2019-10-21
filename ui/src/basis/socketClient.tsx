import { logger } from '../components/app'

export enum MessageType {
  'FETCH_FLOW_DETAILS',
  'FETCH_FLOW',
}

export interface ISocketListener {
  onOpen: (event: Event) => void
  onClose: (event: CloseEvent) => void
  onError: (event: Event) => void
  onMessage: (type: MessageType, data: any) => void
}

interface EventEntry {
  eventType: string
  event: any
}

export class SocketClient {
  private static ws: WebSocket | undefined
  private static listeners = new Map<string, ISocketListener>()
  private static events: EventEntry[] = []

  public static initialize() {
    if (this.ws != null) {
      return
    }

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
    this.ws = ws
  }

  private static onOpen(e: Event) {
    logger.success('connection established', 'WebSocket')
    this.events.push({
      eventType: 'onOpen',
      event: e,
    })
    for (
      let keys = this.listeners.keys(), i = 0;
      i < this.listeners.size;
      i++
    ) {
      const key = keys.next().value
      const value = this.listeners.get(key)
      if (value != null) value.onOpen(e)
    }
  }

  private static onClose(e: CloseEvent) {
    logger.success('connection closed', 'WebSocket')
    this.events.push({
      eventType: 'onClose',
      event: e,
    })
    for (
      let keys = this.listeners.keys(), i = 0;
      i < this.listeners.size;
      i++
    ) {
      const key = keys.next().value
      const value = this.listeners.get(key)
      if (value != null) value.onClose(e)
    }
  }

  private static onError(e: Event) {
    logger.error('error happened', 'WebSocket')
    this.events.push({
      eventType: 'onError',
      event: e,
    })
    for (
      let keys = this.listeners.keys(), i = 0;
      i < this.listeners.size;
      i++
    ) {
      const key = keys.next().value
      const value = this.listeners.get(key)
      if (value != null) value.onError(e)
    }
  }

  private static onMessage(e: MessageEvent) {
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
    logger.info('message received: ' + type, 'Response')

    this.events.push({
      eventType: 'onMessage',
      event: {
        type: type,
        data: data,
      },
    })

    for (
      let keys = this.listeners.keys(), i = 0;
      i < this.listeners.size;
      i++
    ) {
      const key = keys.next().value
      const value = this.listeners.get(key)
      if (value != null) value.onMessage(type, data)
    }
  }

  public static registerListener(
    listenerKey: string,
    socketListener: ISocketListener,
    receiveOldEvents: boolean = true
  ) {
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

  public static unregisterListener(listenerKey: string) {
    this.listeners.delete(listenerKey)
  }

  public static sendMessage(cmd: string, args: object) {
    if (this.ws == null) {
      logger.error('webSocket is not ready', 'WebSocket')
      return
    }
    this.ws.send(
      JSON.stringify({
        cmd: cmd,
        args: args,
      })
    )
  }
}
