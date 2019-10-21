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
import { LogEntry, logger, LogType } from './loggerv2'

export function Logger() {
  const consoleStore = useConsoleStore()
  const themeStore = useThemeStore()

  /*logger = {
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
  }*/

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
