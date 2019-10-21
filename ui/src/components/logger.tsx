import * as React from 'react'
import { Collapse, Pre, ButtonGroup, Tooltip, Button, Divider, Callout, Icon, H6 } from '@blueprintjs/core'
import { Constants } from '../basis/constants'
import { useLoggerStore } from '../stores/loggerStore'
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
};

export type LoggerObject = {
  append: (entry: string, logType: LogType) => void;
  clear: () => void;
  info: (entry: string, tag: string | null) => void;
  success: (entry: string, tag: string | null) => void;
  warning: (entry: string, tag: string | null) => void;
  error: (entry: string, tag: string | null) => void;
};

type LoggerProps = {
  setLogger: null | ((loggerObject: LoggerObject) => void)
}

export const Logger: React.FC<LoggerProps> = (props) => {
  const loggerStore = useLoggerStore()
  const themeStore = useThemeStore()

  const logger = {
    append: (text, logType) => {
      loggerStore.append(text, logType)
    },
    clear: () => {
      loggerStore.clear()
    },
    info: (text, tag) => {
      loggerStore.append(makeLogText(text, tag), LogType.INFO)
    },
    success: (text, tag) => {
      loggerStore.append(makeLogText(text, tag), LogType.SUCCESS)
    },
    warning: (text, tag) => {
      loggerStore.append(makeLogText(text, tag), LogType.WARNING)
    },
    error: (text, tag) => {
      loggerStore.append(makeLogText(text, tag), LogType.ERROR)
    },
  }

  if (props.setLogger != null) {
    props.setLogger(logger)
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

  let lastLogEntry = ''
  let lastLogType = LogType.INFO

  if (loggerStore.logs.length > 0) {
    lastLogEntry = loggerStore.logs[loggerStore.logs.length - 1].entry
    lastLogType = loggerStore.logs[loggerStore.logs.length - 1].type
  }

  return (
    <footer className={'console-footer'}>
      <Callout style={{ display: loggerStore.isOpen ? 'none' : 'flex' }}>
        <Icon
          color={themeStore.isDark ? Constants.COLOR_DARK_ICON : Constants.COLOR_LIGHT_ICON}
          icon="chevron-up"
          onClick={loggerStore.toggleOpen}
          style={{ cursor: 'pointer', paddingRight: '15px' }}
        />
        <div style={{ fontFamily: 'monospace', color: getColor(lastLogType) }}> {lastLogEntry} </div>
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
  const hours = date.getHours().toString().padStart(2, '0')
  const minutes = date.getMinutes().toString().padStart(2, '0')
  const seconds = date.getSeconds().toString().padStart(2, '0')
  const milliseconds = date.getMilliseconds().toString().padStart(4, '0')

  return `${hours}:${minutes}:${seconds}:${milliseconds}`
}

function makeLogText(text: string, tag: string | null = null) {
  return '[' + getDateTime() + '] ' + (tag == null || tag.length === 0 ? '' : tag + ' | ') + text
}
