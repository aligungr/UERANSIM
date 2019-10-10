import * as React from 'react'
import {
  AnchorButton,
  Button,
  ButtonGroup,
  Classes,
  Divider,
  Pre,
  Collapse
} from '@blueprintjs/core'

interface IConsoleState {
  autoScrollBottom: boolean,
  isOpen: boolean
}

export class Console extends React.Component<any, IConsoleState> {
  public static instance: Console
  public static autoScroll: boolean = true

  constructor(props: any) {
    super(props)
    this.state = { autoScrollBottom: true, isOpen: true }
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

  static show() {
    Console.instance.setState({ isOpen:true })
  }

  static hide() {
    Console.instance.setState({ isOpen:false })
  }

  static toggleVisibility() {
    if (Console.instance.state.isOpen) {
      Console.hide();
    }
    else {
      Console.show();
    }
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
        <Collapse keepChildrenMounted={true} isOpen={this.state.isOpen}>
          <Pre
            style={{
              maxHeight: '200px',
              minHeight: '200px',
              display: 'flex',
              padding: '4px',
            }}
          >
            <div style={{ width: '36px' }}>
              <ButtonGroup minimal={false} vertical={true}>
                <Button icon="cross" onClick={(e: any) => Console.clear()} />
                <Button
                  icon="automatic-updates"
                  active={this.state.autoScrollBottom}
                  onClick={(e: any) => Console.toggleAutoScroll()}
                />
              </ButtonGroup>
            </div>
            <Divider />
            <div
              id={'main_bp_console'}
              style={{
                overflow: 'auto',
                width: '100%',
                padding: '8px',
                marginLeft: '6px',
              }}
            />
          </Pre>
        </Collapse>
      </footer>
    )
  }
}
