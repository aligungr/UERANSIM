import * as React from 'react'
import { H1, Button, Classes, Card } from '@blueprintjs/core'
import { Console } from "./Console"

export const Hello = () => {
  let [txt, setTxt] = React.useState('AA')

  return (
    <div style={{ margin: '8px' }}>
      <Button
        className={Classes.DARK}
        text={txt}
        onClick={() => {
          if (console != null) {
            Console.log("slm, " + Math.random() + "")
          }
          setTxt("" + Math.random())
        }}
      />
      <Console/>
    </div>
  )
}
