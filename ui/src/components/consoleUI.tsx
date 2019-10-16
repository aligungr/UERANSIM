import { Button, ButtonGroup, Collapse, Divider, Pre, Tooltip } from '@blueprintjs/core'
import * as React from 'react'
import { Constants } from '../basis/constants'
import { ConsoleContext, ConsoleInfo } from '../contexts/consoleContext'
import { ThemeContext, ThemeInfo } from '../contexts/themeContext'

let idCounter = 1

function getLightColor(className: string): string {
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

function getDarkColor(className: string): string {
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

class LogEntry {
  text: string = ''
  className: string = ''
  entryId: number = 0
}

interface IConsoleState {
  logEntries: LogEntry[]
}

export class ConsoleUI extends React.Component<any, IConsoleState> {
  private autoScroll: boolean = false
  public static instance: ConsoleUI

  constructor(props: any) {
    super(props)
    this.state = {
      logEntries: [],
    }

    ConsoleUI.instance = this
  }

  public appendText(text: string, className: string) {
    idCounter++
    const entry = {
      className,
      text,
      entryId: idCounter,
    }

    const entries = this.state.logEntries
    entries.push(entry)
    this.setState({ logEntries: entries })
    if (this.autoScroll) {
      ConsoleUI.scrollConsoleToBottom();
    }
  }

  public static scrollConsoleToBottom() {
    const element = document.getElementById('bp-console-content')
    if (element == null) throw new Error()
    element.scrollTop = element.scrollHeight
  }

  public clear() {
    this.setState({ logEntries: [] })
  }

  public render() {
    return (
      <ConsoleContext.Consumer>
        {consoleInfo => (
          <ThemeContext.Consumer>
            {themeInfo => this.renderWithContexts(themeInfo, consoleInfo)}
          </ThemeContext.Consumer>
        )}
      </ConsoleContext.Consumer>
    )
  }

  private renderWithContexts(themeInfo: ThemeInfo, consoleInfo: ConsoleInfo) {
    this.autoScroll = consoleInfo.autoScroll

    return (
      <footer className={'console-footer'}>
        <Collapse keepChildrenMounted={true} isOpen={consoleInfo.isOpen}>
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
                <Tooltip content={'Clear Console'}>
                  <Button icon="cross" onClick={(e: any) => this.clear()}/>
                </Tooltip>
                <Tooltip
                  content={`${
                    consoleInfo.autoScroll ? 'Disable' : 'Enable'
                  } Auto Scroll to Bottom`}
                >
                  <Button
                    icon="automatic-updates"
                    active={consoleInfo.autoScroll}
                    onClick={(e: any) => consoleInfo.toggleAutoScroll()}
                  />
                </Tooltip>
              </ButtonGroup>
            </div>
            <Divider/>
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
                      color: themeInfo.isDark
                        ? getDarkColor(value.className)
                        : getLightColor(value.className),
                    }}
                  >
                    {value.text}
                  </div>
                )
              })}
              <br/>
            </div>
          </Pre>
        </Collapse>
      </footer>
    )
  }
}