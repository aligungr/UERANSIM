import { MainContext } from '../contexts/mainContext'
import * as React from 'react'

export function FlowAction() {
  return (
    <MainContext.Consumer>
      {mainInfo => (
        <div>
          Flow action for {mainInfo.mainContent}
        </div>
      )}
    </MainContext.Consumer>
  )
}