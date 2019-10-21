import * as React from 'react'
import { Collapse, Pre, ButtonGroup, Tooltip, Button, Divider, Callout, Icon, H6 } from '@blueprintjs/core'
import { Constants } from '../basis/constants'
import { useLoggerStore } from '../stores/loggerStore'
import { useThemeStore } from '../stores/themeStore';

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
  alert('Error: logger is not ready')
  document.documentElement.innerText = 'Error: logger is not ready'
}

export type LoggerObject = {
  append: (entry: string, logType: LogType) => void;
  clear: () => void;
  success: (entry: string, tag: string | null) => void;
  warning: (entry: string, tag: string | null) => void;
  error: (entry: string, tag: string | null) => void;
  info: (entry: string, tag: string | null) => void;
};

export const LoggerV2: React.FC = () => {
  const loggerStore = useLoggerStore()
  const themeStore = useThemeStore()

  logger = {
    append: (text, logType) => {
      loggerStore.append(text, logType)
    },
    clear: () => {
      loggerStore.clear()
    },
    info: (text, tag) => {
      loggerStore.append(makeLogText(text, tag), LogType.INFO)
    },
    error: (text, tag) => {
      loggerStore.append(makeLogText(text, tag), LogType.ERROR)
    },
    warning: (text, tag) => {
      loggerStore.append(makeLogText(text, tag), LogType.WARNING)
    },
    success: (text, tag) => {
      loggerStore.append(makeLogText(text, tag), LogType.SUCCESS)
    },
  }

  const getColor = React.useCallback(
    (type: LogType) => {
      switch (type) {
        case LogType.INFO:
          return themeStore.isDark ? Constants.COLOR_DARK_LOG_INFO : Constants.COLOR_LIGHT_LOG_INFO
        case LogType.SUCCESS:
          return themeStore.isDark ? Constants.COLOR_DARK_LOG_SUCCESS : Constants.COLOR_LIGHT_LOG_SUCCESS
        case LogType.WARNING:
          return themeStore.isDark ? Constants.COLOR_DARK_LOG_WARNING : Constants.COLOR_LIGHT_LOG_WARNING
        case LogType.ERROR:
          return themeStore.isDark ? Constants.COLOR_DARK_LOG_ERROR : Constants.COLOR_LIGHT_LOG_ERROR
      }
    },
    [themeStore.isDark],
  )

  let lastLogEntry = ""
  let lastLogType = LogType.INFO

  if (loggerStore.logs.length > 0) {
    lastLogEntry = loggerStore.logs[loggerStore.logs.length - 1].entry
    lastLogType = loggerStore.logs[loggerStore.logs.length - 1].type
  }

  return (
    <footer className={'console-footer'}>
      <Callout style={{ display: loggerStore.isOpen ? 'none' : 'flex' }}>
        <Icon
          color="white"
          icon="chevron-up"
          onClick={loggerStore.toggleOpen}
          style={{ cursor: 'pointer', paddingRight: '15px' }}
        />
        <div style={{fontFamily: 'monospace', color: getColor(lastLogType)}}> {lastLogEntry} </div>
      </Callout>

      <Collapse keepChildrenMounted={true} isOpen={loggerStore.isOpen}>
        <Pre
          style={{
            maxHeight: '200px',
            minHeight: '200px',
            display: 'flex',
            padding: '4px',
          }}
        >
          <ButtonGroup minimal={true} vertical={true}>
            <Tooltip content="Minimize console">
              <Button icon="chevron-down" onClick={loggerStore.toggleOpen}/>
            </Tooltip>
            <Tooltip content={'Clear Console'}>
              <Button icon="trash" onClick={(e: any) => loggerStore.clear()}/>
            </Tooltip>
            <Tooltip content={`${loggerStore.autoScroll ? 'Disable' : 'Enable'} Auto Scroll to Bottom`}>
              <Button
                icon="automatic-updates"
                active={loggerStore.autoScroll}
                onClick={loggerStore.toggleAutoScroll}
              />
            </Tooltip>
          </ButtonGroup>
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
            {loggerStore.logs.map((log, i) => {
              return (
                <div
                  key={'' + i}
                  style={{
                    color: getColor(log.type),
                  }}
                >
                  {log.entry}
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
