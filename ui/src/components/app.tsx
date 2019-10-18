import * as React from 'react'
import { Logger, logger } from './logger'
import { Navigation } from './navigation'
import { MainContent } from './mainContent'
import { useThemeStore } from '../basis/stores'

export function App() {
  return (
    <div>
      <Navigation />
      <MainContent />
      <Logger />
    </div>
  )
}
