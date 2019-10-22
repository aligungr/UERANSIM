import * as React from 'react'
import { Button, MenuItem, Spinner } from '@blueprintjs/core'
import { ItemPredicate, ItemRenderer, Select } from '@blueprintjs/select'
import { useFlowSelectionStore } from '../stores'
import { useFlowSelectionApi } from '../hooks/useFlowSelectionApi'

export function FlowSelection() {
  const flowSelectionStore = useFlowSelectionStore()
  const flowSelectionApi = useFlowSelectionApi()

  if (!flowSelectionStore.isLoaded) {
    return <Spinner/>
  }

  return (
    <>
      <FlowSelect
        key={Math.random()}
        items={flowSelectionStore.items}
        itemPredicate={itemFilter}
        noResults={<MenuItem disabled={true} text="No results."/>}
        onItemSelect={(e: string) => {
          flowSelectionStore.setSelected(e)
        }}
        itemRenderer={itemRenderer}>
        <Button
          text={flowSelectionStore.selected == null ? 'Select a Flow' : flowSelectionStore.selected}
          icon="film"
          rightIcon="caret-down"
        />
      </FlowSelect>
      <Button
        style={{ marginLeft: '8px' }}
        disabled={flowSelectionStore.selected == null || !flowSelectionStore.buttonEnabled}
        text={'Select Flow'}
        onClick={() => flowSelectionApi.setCurrentFlow(flowSelectionStore.selected)}
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
