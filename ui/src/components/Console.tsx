import * as React from 'react'
import {
  AnchorButton,
  Button,
  ButtonGroup,
  Classes,
  Divider,
  Pre,
} from '@blueprintjs/core'

class ConsoleState {
  autoScrollBottom: boolean = true
}

export class Console extends React.Component<any, ConsoleState> {
  public static instance: Console
  public static autoScroll: boolean = true

  constructor(props: any) {
    super(props)
    this.state = { autoScrollBottom: true }
    Console.instance = this
  }

  appendText(text: string) {
    const element = document.getElementById('main_bp_console')
    if (element == null) {
      console.error('main_bp_console is undefined')
      return
    }
    const div = document.createElement('div')
    div.innerText = text
    element.append(div)
    if (Console.autoScroll) {
      this.scrollToBottom()
    }
  }

  scrollToBottom() {
    const element = document.getElementById('main_bp_console')
    if (element == null) {
      console.error('main_bp_console is undefined')
      return
    }
    element.scrollTop = element.scrollHeight
  }

  static log(text: string) {
    const instance: Console = Console.instance
    if (instance != null) {
      instance.appendText(text)
    } else {
      alert('Console not ready yet')
      document.documentElement.innerText = text
    }
  }

  static clear() {
    const element = document.getElementById('main_bp_console')
    if (element == null) {
      console.error('main_bp_console is undefined')
      return
    }
    element.innerHTML = ''
  }

  static toggleAutoScroll() {
    const scroll = !Console.autoScroll
    Console.autoScroll = scroll
    Console.instance.setState({ autoScrollBottom: scroll })
    if (scroll) {
      Console.instance.scrollToBottom()
    }
  }

  render() {
    return (
      <footer className={'console-footer'}>
        <Pre
          style={{ maxHeight: '200px', minHeight: '200px', display: 'flex' }}
        >
          <div style={{ width: '30px' }}>
            <ButtonGroup minimal={false} vertical={true}>
              <Button icon="delete" onClick={(e: any) => Console.clear()} />
              <Button
                icon="arrow-down"
                active={this.state.autoScrollBottom}
                onClick={(e: any) => Console.toggleAutoScroll()}
              />
            </ButtonGroup>
          </div>
          <div
            id={'main_bp_console'}
            style={{
              overflow: 'auto',
              backgroundColor: '#202b33',
              width: '100%',
              padding: '8px',
              marginLeft: '16px',
            }}
          />
        </Pre>
      </footer>
    )
  }
}
