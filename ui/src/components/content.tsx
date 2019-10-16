import { Navigation } from './navigation'
import * as React from 'react'
import { Main } from './main'
import { ConsoleUI } from './consoleUI'

export function Content() {
  return (
    <div>
      <Navigation />
      <Main />
      <ConsoleUI />
    </div>
  )
}
