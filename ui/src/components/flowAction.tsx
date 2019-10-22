import * as React from 'react'
import { Button, Card, Elevation } from '@blueprintjs/core'

export function FlowAction() {
  return (
    <div>
      <div style={{ width: '25%' }}>
        <StatesSide/>
      </div>
      <div style={{ width: '75%' }}>
        <StatesSide/>
      </div>
    </div>
  )
}

function StatesSide() {
  return (
    <Card interactive={false} elevation={Elevation.TWO}>
      <h5><a href="#">Card heading</a></h5>
      <p>Card content</p>
      <Button>Submit</Button>
    </Card>
  )
}