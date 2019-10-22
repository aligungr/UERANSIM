import * as React from 'react'
import { ContentType, useContentStore } from '../stores'
import { FlowSelection } from './flowSelection'
import { SocketClient } from '../basis/socketClient'
import { FlowAction } from './flowAction'

export function MainContent() {
  const contentStore = useContentStore()

  React.useEffect(() => {
    SocketClient.initialize()
    return () => {
    }
  })

  let content = <p>no-content</p>

  switch (contentStore.contentType) {
    case ContentType.FLOW_SELECTION:
      content = <FlowSelection/>
      break
    case ContentType.FLOW_ACTION:
      content = <FlowAction/>
      break
  }

  return <div style={{ margin: '8px' }}>{content}</div>
}
