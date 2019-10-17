import * as React from 'react'
import { ContentType, useContentStore } from '../basis/stores'
import { logger } from './logger'
import { MachineSelection } from './machineSelection'
import { SocketClient } from '../basis/socketClient'

export function Content() {
  const contentStore = useContentStore()

  React.useEffect(() => {
    SocketClient.initialize()
    return () => {}
  })

  let content = <p>no-content</p>

  if (contentStore.contentType === ContentType.FLOW_SELECTION) {
    content = <MachineSelection />
  }
  if (contentStore.contentType === ContentType.FLOW_ACTION) {
    content = <div>FLOW_ACTION</div>
  }
  return <div style={{ margin: '8px' }}>{content}</div>
}
