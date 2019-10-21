import * as React from 'react'
import { ContentType, useContentStore } from '../stores/contentStore'
import { FlowSelection } from "./flowSelection"
import { SocketClient } from '../basis/socketClient'

export function MainContent() {
  const contentStore = useContentStore()

  React.useEffect(() => {
    SocketClient.initialize()
    return () => {}
  })

  let content = <p>no-content</p>

  if (contentStore.contentType === ContentType.FLOW_SELECTION) {
    content = <FlowSelection />
  }
  if (contentStore.contentType === ContentType.FLOW_ACTION) {
    content = <div>FLOW_ACTION</div>
  }
  return <div style={{ margin: '8px' }}>{content}</div>
}
