import * as React from 'react'
import { Logger, logger } from './logger'
import { Navigation } from './navigation'
import { Content } from './content'

export function App() {
  return (
    <div>
      <Navigation />
      <Content/>
      <Logger />
    </div>
  )
}