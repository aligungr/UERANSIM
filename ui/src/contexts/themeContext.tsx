import * as React from 'react'
import { ConsoleInfo } from './consoleContext'

export class ThemeInfo {
  isDark: boolean = false
  toggleTheme: () => void = () => { }
}

export const ThemeContext = React.createContext(new ThemeInfo())
