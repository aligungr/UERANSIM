import { useEffect } from 'react'
import { ISocketListener, SocketClient } from '../basis/socketClient'
import { MessageType } from '../basis/messageType'
import { logger } from '../components/app'
import { ContentType, useContentStore, useFlowSelectionStore, useFlowActionStore } from '../stores'

export const useFlowSelectionApi = () => {
  const flowSelectionStore = useFlowSelectionStore()
  const flowActionStore = useFlowActionStore();
  const contentStore = useContentStore()

  useEffect(() => {

    const socketListener: ISocketListener = {
      onOpen: e => {
        SocketClient.sendMessage(MessageType.ALL_FLOWS)
      },
      onClose: e => {
      },
      onError: e => {
      },
      onMessage: (type, data) => {
        switch (type) {
          case MessageType.ALL_FLOWS:
            logger.info(
              'flow names retrieved (total ' + data.length + ')',
              'Response',
            )
            const flowItems: string[] = []
            for (let i = 0; i < data.length; i++) {
              flowItems.push(data[i])
            }
            flowSelectionStore.setIsLoaded(true)
            flowSelectionStore.setSelected(null)
            flowSelectionStore.setItems(flowItems)
            break
          case MessageType.FLOW_SETUP:
            flowActionStore.setMachineInfo(data)
            contentStore.setContent(ContentType.FLOW_ACTION)
            break
        }
      },
    }

    SocketClient.registerListener('flowSelectionApi', socketListener, false)
    return () => {
      SocketClient.unregisterListener('flowSelectionApi')
    }

  }, [])

  return {
    setCurrentFlow: (selected: string | null) => {
      const flowName = selected != null ? selected : ''
      logger.success(`flow selected: ${flowName}`, 'FlowSelection')
      flowSelectionStore.setButtonEnabled(false)
      SocketClient.sendMessage(MessageType.FLOW_SETUP, { arg0: flowName })
    },
  }
}