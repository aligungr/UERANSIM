import * as React from 'react'
import {
  Button,
  ButtonGroup,
  Collapse,
  Divider,
  MenuItem,
  Pre,
  ProgressBar,
  Spinner,
} from '@blueprintjs/core'
import { App } from './App'
import { Console } from './Console'
import { ItemPredicate, ItemRenderer, Select } from '@blueprintjs/select'
import { Main } from './Main'

export interface IFlow {
  title: string
}

let idCounter = 0

const FlowSelect = Select.ofType<IFlow>()

const itemRenderer: ItemRenderer<IFlow> = (
  flow,
  { handleClick, modifiers, query },
  ) => {
  if (!modifiers.matchesPredicate) {
    return null
  }
  idCounter = idCounter + 1;
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

interface IFlowSelectorState {
  selected: IFlow | null
  items: IFlow[]
  loaded: boolean
}

export class FlowSelector extends React.Component<any, IFlowSelectorState> {
  constructor(props: any) {
    super(props)
    this.state = { selected: null, items: [], loaded: false }
  }

  public render() {
    let content = <Spinner/>

    if (this.state.loaded) {
      content = (
        <>
          <FlowSelect
            items={this.state.items}
            itemPredicate={itemFilter}
            noResults={<MenuItem disabled={true} text="No results."/>}
            onItemSelect={(e: IFlow) => this.onItemSelect(e)}
            itemRenderer={itemRenderer}
          >
            <Button
              text={
                this.state.selected == null
                  ? 'Select a Flow'
                  : this.state.selected.title
              }
              icon="film"
              rightIcon="caret-down"
            />
          </FlowSelect>
          <Button
            style={{ marginLeft: '8px' }}
            disabled={this.state.selected == null}
            text={'Run Flow'}
            onClick={() => this.onRunClicked()}
            intent={'primary'}
          />
        </>
      )
    }

    return content
  }

  private onRunClicked() {
    Console.log('run clicked')
  }

  private onItemSelect(item: IFlow) {
    this.setState({ selected: item })
  }

  public startLoading() {
    Main.sendMessage({
      cmd: 'getAllFlows',
      args: {},
    })
  }
}
