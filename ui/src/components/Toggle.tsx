import * as React from 'react'
import { Switch, SwitchProps } from './Switch'

type ToggleContextType = {
  on: boolean
  toggle: () => void
}
const ToggleContext = React.createContext<ToggleContextType>({
  on: false,
  toggle: () => {},
})

function useEffectAfterMount(
  cb: React.EffectCallback,
  dependencies: React.DependencyList
) {
  const justMounted = React.useRef(true)
  React.useEffect(() => {
    if (!justMounted.current) {
      return cb()
    }
    justMounted.current = false
  }, dependencies)
}

interface ToggleProps {
  onToggle: (on: boolean) => void
}
export const Toggle: React.FunctionComponent<ToggleProps> & {
  On: typeof ToggleOn
  Off: typeof ToggleOff
  Button: typeof ToggleButton
} = props => {
  const [on, setOn] = React.useState(false)
  const toggle = React.useCallback(() => setOn(oldOn => !oldOn), [])

  useEffectAfterMount(() => {
    props.onToggle(on)
  }, [on])
  const value = React.useMemo(() => ({ on, toggle }), [on])

  return (
    <ToggleContext.Provider value={value}>
      {props.children}
    </ToggleContext.Provider>
  )
}
Toggle.On = ToggleOn
Toggle.Off = ToggleOff
Toggle.Button = ToggleButton

function useToggleContext() {
  const context = React.useContext(ToggleContext)
  if (!context) {
    throw new Error(
      `Toggle compound components cannot be rendered outside the Toggle component`
    )
  }
  return context
}

function ToggleOn({
  children,
}: React.PropsWithChildren<{}>): React.ReactElement | null {
  const { on } = useToggleContext()
  return on ? (children as React.ReactElement) : null
}

function ToggleOff({
  children,
}: React.PropsWithChildren<{}>): React.ReactElement | null {
  const { on } = useToggleContext()
  return on ? null : (children as React.ReactElement)
}

type Omit<T, U extends keyof T> = Pick<T, Exclude<keyof T, U>>
function ToggleButton(props: Omit<SwitchProps, 'on' | 'onClick'>) {
  const { on, toggle } = useToggleContext()
  return <Switch on={on} onClick={toggle} {...props} />
}
