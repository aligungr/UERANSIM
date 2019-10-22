import * as React from 'react'
import { Button, Card, Elevation, ButtonGroup, AnchorButton } from '@blueprintjs/core'

function columnStyle() {
  const style: React.CSSProperties = {
    float: 'left',
    width: '15%',
    height: '100%',
  }

  return style
}

export function FlowAction() {
  return (
    <div>
      <div style={columnStyle()}><StatesSide/></div>
      <div style={columnStyle()}>b</div>
      <div style={columnStyle()}>c</div>
    </div>
  )
}

function StatesSide() {
  return (
    <Card style={{}} interactive={false} elevation={Elevation.TWO}>
      <ButtonGroup minimal={false} vertical={true}>
        <Button disabled={false} active={true} icon="database">Queries</Button>
        <Button disabled={false} active={true} icon="database">Queries</Button>
        <Button disabled={false} active={true} icon="database">Queries</Button>
        <Button disabled={false} active={true} icon="database">Queries</Button>
        <Button disabled={false} active={true} icon="database">Queries</Button>
      </ButtonGroup>
    </Card>
  )
}