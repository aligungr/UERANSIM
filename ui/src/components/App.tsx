import * as React from 'react'
import { Toggle } from './Toggle'
import { Hello } from './Blue'

export const App = () => (
  <>
    <Hello />
    <Toggle onToggle={on => console.log('on: ', on)}>
      <Toggle.On>The button is on</Toggle.On>
      <Toggle.Off>The button is off</Toggle.Off>
      <Toggle.Button />
    </Toggle>
  </>
)
