import * as React from 'react'
import { Button, ButtonGroup, Collapse, Divider, Pre } from '@blueprintjs/core'
import { Constants } from '../classes/Constants'
import { BaseComponent } from '../basis/BaseComponent'
import { Broadcast } from '../basis/Broadcast'

class LogEntry {
  text: string = ''
  className: string = ''
  entryId: number = 0
}

interface IConsoleState {
  autoScrollBottom: boolean
  isOpen: boolean
  logEntries: LogEntry[]
  isDark: boolean
}

export class ConsoleUI extends BaseComponent<any, IConsoleState> {
  public static instance: ConsoleUI
  public static autoScroll: boolean = true
  private static idCounter: number = 1

  constructor(props: any) {
    super(props)
    this.state = {
      autoScrollBottom: true,
      isOpen: true,
      logEntries: [],
      isDark: Broadcast.isDark(),
    }
    ConsoleUI.instance = this
  }



  static toggleAutoScroll() {
    const scroll = !ConsoleUI.autoScroll
    ConsoleUI.autoScroll = scroll
    ConsoleUI.instance.setState({ autoScrollBottom: scroll })
    if (scroll) {
      ConsoleUI.instance.scrollToBottom()
    }
  }

  static getLightColor(className: string): string {
    switch (className) {
      case 'log-text-normal':
        return Constants.COLOR_LIGHT_LOG_NORMAL
      case 'log-text-success':
        return Constants.COLOR_LIGHT_LOG_SUCCESS
      case 'log-text-warning':
        return Constants.COLOR_LIGHT_LOG_WARNING
      case 'log-text-error':
        return Constants.COLOR_LIGHT_LOG_ERROR
    }
    throw new Error()
  }

  static getDarkColor(className: string): string {
    switch (className) {
      case 'log-text-normal':
        return Constants.COLOR_DARK_LOG_NORMAL
      case 'log-text-success':
        return Constants.COLOR_DARK_LOG_SUCCESS
      case 'log-text-warning':
        return Constants.COLOR_DARK_LOG_WARNING
      case 'log-text-error':
        return Constants.COLOR_DARK_LOG_ERROR
    }
    throw new Error()
  }

  onThemeChanged(isDark: boolean) {
    this.setState({ isDark: isDark })
  }

  onConsoleChanged(isOpen: boolean) {
    this.setState({ isOpen: isOpen })
  }

  appendText(text: string, className: string) {
    ConsoleUI.idCounter = ConsoleUI.idCounter + 1
    const entry = {
      className,
      text,
      entryId: ConsoleUI.idCounter,
    }

    const entries = this.state.logEntries
    entries.push(entry)
    this.setState({ logEntries: entries })
    if (ConsoleUI.autoScroll) {
      this.scrollToBottom()
    }
  }

  scrollToBottom() {
    const element = document.getElementById('bp-console-content')
    if (element == null) throw new Error()
    element.scrollTop = element.scrollHeight
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
                  onClick={(e: any) => ConsoleUI.toggleAutoScroll()}
                />
              </ButtonGroup>
            </div>
            <Divider />
            <div
              id={'bp-console-content'}
              style={{
                overflow: 'auto',
                width: '100%',
                padding: '8px',
                marginLeft: '6px',
              }}
            >
              {this.state.logEntries.map(value => {
                return (
                  <div
                    key={'' + value.entryId}
                    style={{
                      color: this.state.isDark
                        ? ConsoleUI.getDarkColor(value.className)
                        : ConsoleUI.getLightColor(value.className),
                    }}
                  >
                    {value.text}
                  </div>
                )
              })}
              <br />
            </div>
          </Pre>
        </Collapse>
      </footer>
    )
  }
}
