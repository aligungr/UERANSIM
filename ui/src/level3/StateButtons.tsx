import { BaseComponent } from '../basis/BaseComponent'
import * as React from 'react'
import { AnchorButton, Button, ButtonGroup } from '@blueprintjs/core'
import { Console } from '../basis/Console'

export class StateButtons extends BaseComponent<any, any> {
  constructor(props: any) {
    super(props)
  }

  public render() {
    return (
      <div>
        <div>
          <AnchorButton
            intent={'none'}
            icon="function"
            rightIcon="blank"
            active={true}
          >
            Options
          </AnchorButton>
          <AnchorButton
            intent={'none'}
            icon="function"
            rightIcon="blank"
            active={true}
          >
            Options
          </AnchorButton>
        </div>
        <div>
          <AnchorButton
            intent={'none'}
            icon="function"
            rightIcon="blank"
            active={true}
          >
            Options
          </AnchorButton>
          <AnchorButton
            intent={'none'}
            icon="function"
            rightIcon="blank"
            active={true}
          >
            Options
          </AnchorButton>
        </div>
      </div>
    )
  }
}
