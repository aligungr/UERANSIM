import * as React from 'react'
import { H1, Button } from '@blueprintjs/core'

export const Hello = () => {
  let [txt, setTxt] = React.useState('AA')
  return <Button text={txt} onClick={() => setTxt(Math.random().toFixed(3))} />
}
