import { useEffect, useState, useCallback } from 'react'
import { useLoggerStore } from '../stores/loggerStore'
import { logger } from '../components/app'

export type Event = {
  type: 'AMF_DOWN' | 'AMF_UP';
  data: any;
};
// TODO: Make it reusable or shove everyting in?
export const useApi = (url: string) => {
  const consoleStore = useLoggerStore()
  const [ws, setWs] = useState<WebSocket | null>()
  const send = useCallback(
    (data: string) => {
      ws && ws.send(data)
    },
    [ws],
  )

  const close = useCallback(
    () => {
      if (ws) {
        console.log('Closing ws')
        logger.info('Closing ws', 'useApi')
        ws.close()
      }
    },
    [ws],
  )

  const handleEvents = useCallback((event: Event) => {
    switch (event.type) {
      case 'AMF_DOWN':
        break
      case 'AMF_UP':
        break
    }
  }, [])

  useEffect(() => {
    const _ws = new WebSocket(url)
    _ws.onopen = (e: WebSocketEventMap['open']) => {
      logger.info('Connection Successful', 'useApi')
    }
    _ws.onmessage = (e: WebSocketEventMap['message']) => {
      logger.info(`Message received ${JSON.stringify(e.data)}`, 'useApi')
    }
    _ws.onerror = (e: WebSocketEventMap['error']) => {
      logger.error(`Sth went wrong`, 'useApi')
    }
    _ws.onclose = (e: WebSocketEventMap['close']) => {
      logger.warning('Socket closed', 'useApi')
    }
    setWs(_ws),
    () => {
      logger.info('Closing ws', 'useApi')
      console.log('Closing ws')
      _ws.close()
    }
  }, [])

  return [send, close]
}
