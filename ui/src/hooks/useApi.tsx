import { useEffect, useState, useCallback } from 'react'
import { useLoggerStore, useFlowActionStore, useAppStore } from '../stores'
import { logger } from '../components/app'
import { SocketClient, ISocketListener } from '../basis/socketClient'

export type Event = {
  type: 'AMF_DOWN' | 'AMF_UP'
  data: any
}

let ws

export const useApi = () => {
  const logStore = useLoggerStore()
  const flowStore = useFlowActionStore()
  const appStore = useAppStore()
  useEffect(() => {
    
    // ws = SocketClient.registerListener("API")
    const sockListener: ISocketListener = {
      onOpen: e => logger.info("App is ready"),
      onClose: e => {},
      onError: e => {},
      onMessage: (type, data) => {
          switch (e.type) {
            case  
                flowStore.setMachineInfo(e.payload)
                break
              
          }
      }  
    }
    SocketClient.registerListener("API", sockListener)
  }, [])

  return {
    setCurrentFlow: (name: string) => {
      ws.send('FETCH_FLOW_DETAIL')
      appStore.setNewFlowLoading(true)
      logger.info(`Loading new flow: ${name}`, 'FLOW')
    },
    refreshAvailableFlowNames: () => {
      ws.send('FETCH_FLOW_NAMES')
    }
  }
}
































// TODO: Make it reusable or shove everyting in?
// export const useApi = (url: string) => {

//   const consoleStore = useLoggerStore()
//   const [ws, setWs] = useState<WebSocket | null>()
//   const send = useCallback(
//     (data: string) => {
//       ws && ws.send(data)
//     },
//     [ws],
//   )

//   const close = useCallback(
//     () => {
//       if (ws) {
//         console.log('Closing ws')
//         logger.info('Closing ws', 'useApi')
//         ws.close()
//       }
//     },
//     [ws],
//   )

//   const handleEvents = useCallback((event: Event) => {
//     switch (event.type) {
//       case 'AMF_DOWN':
//         break
//       case 'AMF_UP':
//         break
//     }
//   }, [])

//   useEffect(() => {
//     const _ws = new WebSocket(url)
//     _ws.onopen = (e: WebSocketEventMap['open']) => {
//       logger.info('Connection Successful', 'useApi')
//     }
//     _ws.onmessage = (e: WebSocketEventMap['message']) => {
//       logger.info(`Message received ${JSON.stringify(e.data)}`, 'useApi')
//     }
//     _ws.onerror = (e: WebSocketEventMap['error']) => {
//       logger.error(`Sth went wrong`, 'useApi')
//     }
//     _ws.onclose = (e: WebSocketEventMap['close']) => {
//       logger.warning('Socket closed', 'useApi')
//     }
//     setWs(_ws),
//       () => {
//         logger.info('Closing ws', 'useApi')
//         console.log('Closing ws')
//         _ws.close()
//       }
//   }, [])

//   return {}
// }
