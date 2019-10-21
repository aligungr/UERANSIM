import * as React from 'react'
import {
  Button,
  ButtonGroup,
  Collapse,
  Divider,
  Pre,
  Tooltip,
} from '@blueprintjs/core'
import { Constants } from '../basis/constants'
import { useConsoleStore } from '../stores/consoleStore'
import { useThemeStore } from '../stores/themeStore'

export enum LogType {
  'INFO',
  'SUCCESS',
  'WARNING',
  'ERROR',
}

export type LogEntry = {
  entry: string;
  type: LogType;
  id: number;
};

export let logger: LoggerObject = {
  append: (text: string, logType: LogType) => notInitYet(),
  clear: () => notInitYet(),
  info: (text: string, tag: string | null = null) => notInitYet(),
  error: (text: string, tag: string | null = null) => notInitYet(),
  success: (text: string, tag: string | null = null) => notInitYet(),
  warning: (text: string, tag: string | null = null) => notInitYet(),
}

function notInitYet() {
  //alert('Error: logger is not ready')
  //document.documentElement.innerText = 'Error: logger is not ready'
}

export type LoggerObject = {
  append: (entry: string, logType: LogType) => void;
  clear: () => void;
  success: (entry: string, tag: string | null) => void;
  warning: (entry: string, tag: string | null) => void;
  error: (entry: string, tag: string | null) => void;
  info: (entry: string, tag: string | null) => void;
};


export function Logger() {
  const consoleStore = useConsoleStore()
  const themeStore = useThemeStore()

  logger = {
    append: (text, logType) => {
      consoleStore.appendText(text, logType)
    },
    clear: () => {
      consoleStore.clear()
    },
    info: (text, tag) => {
      consoleStore.appendText(makeLogText(text, tag), LogType.INFO)
    },
    error: (text, tag) => {
      consoleStore.appendText(makeLogText(text, tag), LogType.ERROR)
    },
    warning: (text, tag) => {
      consoleStore.appendText(makeLogText(text, tag), LogType.WARNING)
    },
    success: (text, tag) => {
      consoleStore.appendText(makeLogText(text, tag), LogType.SUCCESS)
    },
  }

  const getColor = React.useCallback(
    (type: LogType) => {
      switch (type) {
        case LogType.INFO:
          return themeStore.isDark ? Constants.COLOR_LIGHT_LOG_INFO : Constants.COLOR_DARK_LOG_INFO;
        case LogType.SUCCESS:
          return themeStore.isDark ? Constants.COLOR_LIGHT_LOG_SUCCESS : Constants.COLOR_DARK_LOG_SUCCESS;
        case LogType.WARNING:
          return themeStore.isDark ? Constants.COLOR_LIGHT_LOG_WARNING : Constants.COLOR_DARK_LOG_WARNING;
        case LogType.ERROR:
          return themeStore.isDark ? Constants.COLOR_LIGHT_LOG_ERROR : Constants.COLOR_DARK_LOG_ERROR;
      }
    },
    [ themeStore.isDark ]
  );

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
                <Button
                  icon="cross"
                  onClick={(e: any) => consoleStore.clear()}
                />
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
            {consoleStore.logs.map((value: LogEntry) => {
              return (
                <div
                  key={'' + value.id}
                  style={{
                    color: getColor(value.type),
                  }}
                >
                  {value.entry}
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
      return Constants.COLOR_LIGHT_LOG_INFO
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
      return Constants.COLOR_DARK_LOG_INFO
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
  return (
    '[' +
    getDateTime() +
    '] ' +
    (tag == null || tag.length === 0 ? '' : tag + ' | ') +
    text
  )
}
