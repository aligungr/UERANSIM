import * as React from 'react'
import { H1, Button, Classes, Card } from '@blueprintjs/core'

export const Hello = () => {
  let [txt, setTxt] = React.useState('AA')

  return (
    <div style={{margin: "8px"}}>
      <Button
        className={Classes.DARK}
        text={txt}
        onClick={() => setTxt(Math.random().toFixed(3))}
      />
    </div>
  )
}

export const aFunc = () => {}
