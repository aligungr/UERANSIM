import * as React from 'react'
import { Button, ButtonGroup, Collapse, Divider, Pre, Tooltip } from '@blueprintjs/core'
import { ConsoleLogEntry, useConsoleStore, useThemeStore } from '../basis/stores'
import { Constants } from '../basis/constants'

export let logger = {
  appendText: (text: string, className: string) => { notInitYet() },
  clear: () => { notInitYet() },
  log: (text: string, tag: string | null = null) => { notInitYet() },
  error: (text: string, tag: string | null = null) => { notInitYet() },
  success: (text: string, tag: string | null = null) => { notInitYet() },
  warning: (text: string, tag: string | null = null) => { notInitYet() },
}

function notInitYet() {
  alert("Error: logger is not ready")
  document.documentElement.innerText = "Error: logger is not ready"
}

export function Logger() {
  const consoleStore = useConsoleStore()
  const themeStore = useThemeStore()

  logger = {
    appendText: (text, className) => {
      consoleStore.appendText(text, className)
    },
    clear: () => {
      consoleStore.clear()
    },
    log: (text, tag) => {
      consoleStore.appendText(makeLogText(text, tag), 'log-text-normal')
    },
    error: (text, tag) => {
      consoleStore.appendText(makeLogText(text, tag), 'log-text-error')
    },
    warning: (text, tag) => {
      consoleStore.appendText(makeLogText(text, tag), 'log-text-warning')
    },
    success: (text, tag) => {
      consoleStore.appendText(makeLogText(text, tag), 'log-text-success')
    },
  }

  return (
    <footer className={'console-footer'}>
      <Collapse keepChildrenMounted={true} isOpen={consoleStore.isOpen}>
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
                <Button icon="cross" onClick={(e: any) => consoleStore.clear()}/>
              </Tooltip>
              <Tooltip
                content={`${
                  consoleStore.autoScroll ? 'Disable' : 'Enable'
                } Auto Scroll to Bottom`}
              >
                <Button
                  icon="automatic-updates"
                  active={consoleStore.autoScroll}
                  onClick={(e: any) => consoleStore.toggleAutoScroll()}
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
            {consoleStore.logs.map((value: ConsoleLogEntry) => {
              return (
                <div
                  key={'' + value.entryId}
                  style={{
                    color: themeStore.isDark
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

function getDateTime() {
  const date = new Date()
  return (
    date
      .getHours()
      .toString()
      .padStart(2, '0') +
    ':' +
    date
      .getMinutes()
      .toString()
      .padStart(2, '0') +
    ':' +
    date
      .getSeconds()
      .toString()
      .padStart(2, '0') +
    ':' +
    date
      .getMilliseconds()
      .toString()
      .padStart(4, '0')
  )
}

function makeLogText(text: string, tag: string | null = null) {
  return '[' +
    getDateTime() +
    '] ' +
    (tag == null || tag.length === 0 ? '' : tag + ' | ') +
    text
}