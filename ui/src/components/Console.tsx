import * as React from 'react'
import { Button, ButtonGroup, Collapse, Divider, Pre } from '@blueprintjs/core'
import { App } from './App'
import { Constants } from './Constants'

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

export class Console extends React.Component<any, IConsoleState> {
  public static instance: Console
  public static autoScroll: boolean = true
  private static idCounter: number = 1

  constructor(props: any) {
    super(props)
    this.state = {
      autoScrollBottom: true,
      isOpen: true,
      logEntries: [],
      isDark: App.isDark,
    }
    Console.instance = this
  }

  static getDateTime(): string {
    const date = new Date()
    return (
      date.getHours() +
      ':' +
      date.getMinutes() +
      ':' +
      date.getSeconds() +
      ':' +
      date.getMilliseconds()
    )
  }

  static log(text: string, tag: string | null = null) {
    const entry =
      '[' +
      this.getDateTime() +
      '] INFO ' +
      (tag == null || tag.length === 0 ? '' : tag + ': ') +
      text

    const instance: Console = Console.instance
    if (instance != null) {
      instance.appendText(entry, 'log-text-normal')
    } else {
      alert('Console not ready yet')
      document.documentElement.innerText = entry
    }
  }

  static success(text: string, tag: string | null = null) {
    const entry =
      '[' +
      this.getDateTime() +
      '] INFO ' +
      (tag == null || tag.length === 0 ? '' : tag + ': ') +
      text

    const instance: Console = Console.instance
    if (instance != null) {
      instance.appendText(entry, 'log-text-success')
    } else {
      alert('Console not ready yet')
      document.documentElement.innerText = entry
    }
  }

  static error(text: string, tag: string | null = null) {
    const entry =
      '[' +
      this.getDateTime() +
      '] INFO ' +
      (tag == null || tag.length === 0 ? '' : tag + ': ') +
      text

    const instance: Console = Console.instance
    if (instance != null) {
      instance.appendText(entry, 'log-text-error')
    } else {
      alert('Console not ready yet')
      document.documentElement.innerText = entry
    }
  }

  static warn(text: string, tag: string | null = null) {
    const entry =
      '[' +
      this.getDateTime() +
      '] INFO ' +
      (tag == null || tag.length === 0 ? '' : tag + ': ') +
      text

    const instance: Console = Console.instance
    if (instance != null) {
      instance.appendText(entry, 'log-text-warning')
    } else {
      alert('Console not ready yet')
      document.documentElement.innerText = entry
    }
  }

  static clear() {
    const element = document.getElementById('bp-console-content')
    if (element == null) throw new Error()
    element.innerHTML = ''
  }

  static show() {
    Console.instance.setState({ isOpen: true })
  }

  static hide() {
    Console.instance.setState({ isOpen: false })
  }

  static toggleVisibility() {
    if (Console.instance.state.isOpen) {
      Console.hide()
    } else {
      Console.show()
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

  static setDark(isDark: boolean) {
    if (Console.instance == null) {
      return
    }
    Console.instance.setState({ isDark })
  }

  appendText(text: string, className: string) {
    Console.idCounter = Console.idCounter + 1
    const entry = {
      className,
      text,
      entryId: Console.idCounter,
    }

    const entries = this.state.logEntries
    entries.push(entry)
    this.setState({ logEntries: entries })
    if (Console.autoScroll) {
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
                  onClick={(e: any) => Console.toggleAutoScroll()}
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
                        ? Console.getDarkColor(value.className)
                        : Console.getLightColor(value.className),
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
