import * as React from 'react'
import { Button, MenuItem, Spinner } from '@blueprintjs/core'
import { Console } from '../level1/Console'
import { ItemPredicate, ItemRenderer, Select } from '@blueprintjs/select'
import { MainContent } from '../level1/Main'
import { BaseComponent } from '../basis/BaseComponent'
import { Broadcast } from '../basis/Broadcast'

export interface IFlow {
  title: string
}

let idCounter = 0

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

interface IFlowSelectorState {
  selected: IFlow | null
  items: IFlow[]
  loaded: boolean
}

export class FlowSelection extends BaseComponent<any, IFlowSelectorState> {
  constructor(props: any) {
    super(props)
    this.state = { selected: null, items: [], loaded: false }
  }

  onSocketMessage(type: string, data: any) {
    if (type === 'allFlows') {
      Console.log(
        'flow names retrieved (total ' + data.length + ')',
        'Response'
      )
      const flowItems: IFlow[] = []
      for (let i = 0; i < data.length; i = i + 1) {
        flowItems.push({
          title: data[i],
        })
      }
      this.setState({
        loaded: true,
        selected: null,
        items: flowItems,
      })
    } else if (type === 'machineSetup') {
      Broadcast.setMainContent(MainContent.FLOW_ACTION)
      Broadcast.setMachineInfo(data)
    }
  }

  public render() {
    let content = <Spinner />

    if (this.state.loaded) {
      content = (
        <>
          <FlowSelect
            items={this.state.items}
            itemPredicate={itemFilter}
            noResults={<MenuItem disabled={true} text="No results." />}
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
    let flowTitle = ''
    if (this.state.selected != null) {
      flowTitle = this.state.selected.title
    }

    Broadcast.setMainContent(MainContent.FLOW_ACTION)
    Console.log('flow selected: ' + flowTitle)

    Broadcast.sendSocketMessage('setupFlow', { arg0: flowTitle })
  }

  private onItemSelect(item: IFlow) {
    this.setState({ selected: item })
  }

  public onSocketConnected(e: Event) {
    Broadcast.sendSocketMessage('getAllFlows', {})
  }
}
