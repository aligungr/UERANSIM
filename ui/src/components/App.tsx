import * as React from 'react'
import { Hello } from './Blue'
import { SocketClient } from './SocketClient'

export const App = () => {
  let ws = new SocketClient()

  React.useEffect(() => {
    document.body.className = 'bp3-dark'
  }, [])
  return <Hello />
}
