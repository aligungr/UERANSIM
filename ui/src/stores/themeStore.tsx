import { Classes } from '@blueprintjs/core'
import { Constants } from '../basis/constants'
import { create } from 'zustand'

function updateBodyForTheme(isDark: boolean) {
  const body = document.getElementsByTagName('body')[0]
  if (isDark) {
    body.setAttribute('class', Classes.DARK)
    body.style.backgroundColor = Constants.COLOR_DARK_BACKGROUND
  } else {
    body.setAttribute('class', '')
    body.style.backgroundColor = Constants.COLOR_LIGHT_BACKGROUND
  }
}

export const [useThemeStore] = create<ThemeStore>(set => ({
  isDark: true,
  toggleTheme: () =>
    set(state => {
      const isDark = !state.isDark
      updateBodyForTheme(isDark)
      return { isDark: isDark }
    }),
}))

export type ThemeStore = {
  isDark: boolean,
  toggleTheme(): void
}