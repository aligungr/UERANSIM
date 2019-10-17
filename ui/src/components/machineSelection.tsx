import * as React from 'react'
import { Button, MenuItem, Spinner } from '@blueprintjs/core'
import { ISocketListener, SocketClient } from '../basis/socketClient'
import { logger } from './logger'
import { ItemPredicate, ItemRenderer, Select } from '@blueprintjs/select'

export function MachineSelection() {
  const [isLoaded, setLoaded] = React.useState(false)
  const [selected, setSelected] = React.useState(null as IFlow | null)
  const [items, setItems] = React.useState([] as IFlow[])

  const socketListener: ISocketListener = {
    onOpen: () => {
      SocketClient.sendMessage('getAllFlows', {})
    },
    onMessage: (type, data) => {
      if (type === 'allFlows') {
        logger.log(
          'flow names retrieved (total ' + data.length + ')',
          'Response'
        )
        const flowItems: IFlow[] = []
        for (let i = 0; i < data.length; i = i + 1) {
          flowItems.push({
            title: data[i],
          })
        }
        setLoaded(true)
        setSelected(null)
        setItems(flowItems)
      } else if (type === 'machineSetup') {
        // Broadcast.setMachineInfo(data)
        // Broadcast.setMainContent(MainContent.FLOW_ACTION)
      }
    },
    onClose: () => {},
    onError: () => {},
  }

  React.useEffect(() => {
    SocketClient.registerListener('machineSelection', socketListener, false)
    return () => {
      SocketClient.unregisterListener('machineSelection')
    }
  })

  if (!isLoaded) {
    return <Spinner />
  }

  return (
    <>
      <FlowSelect
        items={items}
        itemPredicate={itemFilter}
        noResults={<MenuItem disabled={true} text="No results." />}
        onItemSelect={(e: IFlow) => {
          setSelected(e)
        }}
        itemRenderer={itemRenderer}
      >
        <Button
          text={selected == null ? 'Select a Flow' : selected.title}
          icon="film"
          rightIcon="caret-down"
        />
      </FlowSelect>
      <Button
        style={{ marginLeft: '8px' }}
        disabled={selected == null}
        text={'Select Flow'}
        onClick={() => {
          const flowTitle = selected != null ? selected.title : ''
          logger.success(`flow selected: ${flowTitle}`)
        }}
        intent={'primary'}
      />
    </>
  )
}

export interface IFlow {
  title: string
}

let idCounter = 1
const FlowSelect = Select.ofType<IFlow>()

const itemRenderer: ItemRenderer<IFlow> = (
  flow,
  { handleClick, modifiers, query }
) => {
  if (!modifiers.matchesPredicate) {
    return null
  }
  idCounter = idCounter + 1
  return (
    <MenuItem
      key={idCounter}
      active={modifiers.active}
      disabled={modifiers.disabled}
      onClick={handleClick}
      text={flow.title}
    />
  )
}

const itemFilter: ItemPredicate<IFlow> = (query, flow, _index, exactMatch) => {
  const normalizedTitle = flow.title.toLowerCase().trim()
  const normalizedQuery = query.toLowerCase().trim()
  return normalizedTitle.includes(normalizedQuery)
}
