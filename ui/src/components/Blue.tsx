import React, { useState } from 'react'
import { H1, Button } from '@blueprintjs/core'

export const Hello = () => {
  let [txt, setTxt] = useState('AA')
  return <Button text={txt} onClick={() => setTxt(Math.random().toFixed(3))} />
}
