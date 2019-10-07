import * as React from 'react'
import { render, fireEvent, waitForElement } from 'react-testing-library'

import { App } from '../App'

/**
 * Just a simple integration tests
 */

test('App Component renders with toggle switch', () => {
  const wrap = render(<App />)

  expect(wrap.getByTestId('toggle-input')).toBeInTheDocument()
})

test('App Component can change toggle status with clicking toggle switch', async () => {
  const { container, getByTestId } = render(<App />)
  const toggleButton = getByTestId('toggle-input')
  expect(container).toHaveTextContent(/the button is off/i)

  fireEvent.click(toggleButton)
  expect(container).toHaveTextContent(/the button is on/i)
  expect(toggleButton).toSatisfy(button => button.checked)

  fireEvent.click(toggleButton)
  expect(container).toHaveTextContent(/the button is off/i)
  expect(toggleButton).toSatisfy(button => !button.checked)
})
