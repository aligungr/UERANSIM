import * as React from 'react'
import { Hello } from './Blue'

export const App = () => {
  React.useEffect(() => {
    document.body.className = 'bp3-dark'
  }, [])
  return <Hello />
}
