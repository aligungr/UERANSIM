import './switch.styles.css'
import * as React from 'react'

const noop = () => {}

export interface SwitchProps extends React.HTMLProps<HTMLLabelElement> {
  on: boolean
  onClick: () => void
}

export function Switch({
  on,
  className = '',
  onClick,
  'aria-label': ariaLabel,
  ...props
}: SwitchProps) {
  const btnClassName = React.useMemo(
    () =>
      [className, 'toggle-btn', on ? 'toggle-btn-on' : 'toggle-btn-off']
        .filter(Boolean)
        .join(' '),
    [on]
  )

  return (
    <label aria-label={ariaLabel || 'Toggle'} style={{ display: 'block' }}>
      <input
        className="toggle-input"
        type="checkbox"
        checked={on}
        onChange={noop}
        onClick={onClick}
        data-testid="toggle-input"
      />
      <span className={btnClassName} {...props} />
    </label>
  )
}
