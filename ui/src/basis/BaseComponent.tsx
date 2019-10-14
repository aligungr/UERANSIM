import * as React from 'react'
import { MainContent } from '../level1/Main'

const mountedComponents: BaseComponent[] = []

export abstract class BaseComponent<P = {}, S = {}> extends React.Component<
  P,
  S
> {
  componentDidMount(): void {
    mountedComponents.push(this)
  }

  componentWillUnmount(): void {
    const comp = mountedComponents
    for (const key in comp) {
      const val = comp[key]
      if (val === this) {
        delete mountedComponents[key]
      }
    }
  }

  public static getComponents(): BaseComponent[] {
    return mountedComponents
  }

  public onThemeChanged(isDark: boolean) {}

  public onConsoleChanged(isOpen: boolean) {}

  public onMainContentChanged(mainContent: MainContent) {}

  public onSocketConnected(e: Event) {}

  public onSocketClosed(e: CloseEvent) {}

  public onSocketError(e: Event) {}

  public onSocketMessage(type: string, data: any) {}
}
