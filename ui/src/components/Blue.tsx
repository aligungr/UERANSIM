import * as React from 'react'
import { H1, Button, Classes, Card } from '@blueprintjs/core'

export const Hello = () => {
  let [txt, setTxt] = React.useState('AA')
  return (
    <Button
      className={Classes.DARK}
      text={txt}
      onClick={() => setTxt(Math.random().toFixed(3))}
    />
  )
}

export const aFunc = () => {}
