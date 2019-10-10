import * as React from 'react'
import { Button, Classes, Pre } from '@blueprintjs/core'

export class Console extends React.Component<any, any> {
  public static instance: Console;
  public static autoScroll: boolean = true;

  constructor(props: any) {
    super(props)
    Console.instance = this;
  }

  appendText(text: string) {
    const element = document.getElementById("main_bp_console");
    if (element == null) {
      console.error("main_bp_console is undefined");
      return;
    }
    const div = document.createElement("div");
    div.innerText = text;
    element.append(div);
    if (Console.autoScroll) {
      element.scrollTop = element.scrollHeight;
    }
  }

  static log(text: string) {
    const instance: Console = Console.instance;
    if (instance != null) {
      instance.appendText(text);
    } else {
      alert("Console not ready yet");
      document.documentElement.innerText = text;
    }
  }

  render() {
    return (
      <Pre id={"main_bp_console"} style={{overflow: 'auto', maxHeight: '200px', minHeight: '200px'}}/>
    )
  }
}