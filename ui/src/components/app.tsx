import * as React from 'react';
import { Navigation } from './navigation';
import { MainContent } from './mainContent';
import { Logger, LoggerObject, LogType } from './logger'
import { SocketClient } from '../basis/socketClient'

export let logger: LoggerObject = {
	append: (text: string, logType: LogType) => notInitYet(),
	clear: () => notInitYet(),
	info: (text: string, tag: string | null = null) => notInitYet(),
	success: (text: string, tag: string | null = null) => notInitYet(),
	warning: (text: string, tag: string | null = null) => notInitYet(),
	error: (text: string, tag: string | null = null) => notInitYet(),
}

function notInitYet() {
	alert('Error: logger is not ready')
	document.documentElement.innerText = 'Error: logger is not ready'
}

function setLogger(loggerObject: LoggerObject) {
	logger = loggerObject
}

export function App() {
	SocketClient.initialize()

	return (
		<div>
			<Navigation />
			<MainContent />
			<Logger setLogger={setLogger} />
		</div>
	);
}
