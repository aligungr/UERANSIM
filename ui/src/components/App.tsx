import * as React from 'react'

import { Hello } from './Hello'
import { Navigation } from "./Navigation";

export const App = () => {
  return (
    <div>
      <Navigation/>
      <Hello/>
    </div>
  );
}
