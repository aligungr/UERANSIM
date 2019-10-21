import * as React from 'react'
import { Button, MenuItem, Spinner } from '@blueprintjs/core'
import { ISocketListener, SocketClient } from '../basis/socketClient'
import { logger } from './logger'
import { ItemPredicate, ItemRenderer, Select } from '@blueprintjs/select'
import { ContentType, useContentStore } from '../stores/contentStore'
import { useFlowActionStore } from '../stores/flowActionStore'

export function FlowSelection() {
  const [isLoaded, setLoaded] = React.useState(false)
  const [selected, setSelected] = React.useState(null as string | null)
  const [items, setItems] = React.useState([] as string[])
  const [buttonEnabled, setButtonEnabled] = React.useState(true)
  const contentStore = useContentStore()
  const flowActionStore = useFlowActionStore()

  const socketListener: ISocketListener = {
    onOpen: () => {
      SocketClient.sendMessage('getAllFlows', {})
    },
    onMessage: (type, data) => {
      if (type === 'allFlows') {
        logger.info(
          'flow names retrieved (total ' + data.length + ')',
          'Response',
        )
        const flowItems: string[] = []
        for (let i = 0; i < data.length; i = i + 1) {
          flowItems.push(data[i])
        }
        setLoaded(true)
        setSelected(null)
        setItems(flowItems)
      } else if (type === 'machineSetup') {
        flowActionStore.setMachineInfo(data)
        contentStore.setContent(ContentType.FLOW_ACTION)
      }
    },
    onClose: () => {
    },
    onError: () => {
    },
  }

  React.useEffect(() => {
    SocketClient.registerListener('flowSelection', socketListener, false)
    return () => {
      SocketClient.unregisterListener('flowSelection')
    }
  })

  if (!isLoaded) {
    return <Spinner/>
  }

  return (
    <>
      <FlowSelect
        key={Math.random()}
        items={items}
        itemPredicate={itemFilter}
        noResults={<MenuItem disabled={true} text="No results."/>}
        onItemSelect={(e: string) => {
          setSelected(e)
        }}
        itemRenderer={itemRenderer}
      >
        <Button
          text={selected == null ? 'Select a Flow' : selected}
          icon="film"
          rightIcon="caret-down"
        />
      </FlowSelect>
      <Button
        style={{ marginLeft: '8px' }}
        disabled={selected == null || !buttonEnabled}
        text={'Select Flow'}
        onClick={() => {
          const flowName = selected != null ? selected : ''
          logger.success(`flow selected: ${flowName}`, "FlowSelection")
          setButtonEnabled(false)
          SocketClient.sendMessage('setupFlow', {arg0: flowName})
        }}
        intent={'primary'}
      />
    </>
  )
}

let idCounter = 1
const FlowSelect = Select.ofType<string>()

const itemRenderer: ItemRenderer<string> = (flow, { handleClick, modifiers, query }) => {
  if (!modifiers.matchesPredicate) {
    return null
  }
  idCounter++
  return (
    <MenuItem
      key={idCounter}
      active={modifiers.active}
      disabled={modifiers.disabled}
      onClick={handleClick}
      text={flow}
    />
  )
}

const itemFilter: ItemPredicate<string> = (query, flow, _index, exactMatch) => {
  const normalizedTitle = flow.toLowerCase().trim()
  const normalizedQuery = query.toLowerCase().trim()
  return normalizedTitle.includes(normalizedQuery)
}
