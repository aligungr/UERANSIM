import * as React from 'react'
import {
  Alignment,
  AnchorButton,
  Classes,
  Navbar,
  NavbarGroup,
  NavbarHeading,
  NavbarDivider,
} from '@blueprintjs/core'

export interface NavigationProps {}

export class Navigation extends React.PureComponent<NavigationProps> {
  public render() {
    return (
      <Navbar>
        <NavbarGroup align={Alignment.LEFT}>
          <NavbarHeading>UERANSIM</NavbarHeading>
          <NavbarDivider />
          <AnchorButton
            href="#href"
            text="Docs"
            target="_blank"
            minimal={true}
            rightIcon="share"
          />
          <AnchorButton
            href="http://github.com/aligungr/ue-ran-sim"
            text="Github"
            target="_blank"
            minimal={true}
            rightIcon="code"
          />
        </NavbarGroup>
      </Navbar>
    )
  }
}
