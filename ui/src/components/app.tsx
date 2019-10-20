import * as React from 'react';
import { Logger, logger } from './logger';
import { Navigation } from './navigation';
import { MainContent } from './mainContent';
import { useThemeStore } from '../basis/stores';
import { useLogStore } from '../stores';
import { LoggerV2 } from './loggerv2';
import { Button, H5, ButtonGroup } from '@blueprintjs/core';
import { useApi } from '../hooks/useApi';

export function App() {
	const logStore = useLogStore();
	const [ a, setA ] = React.useState(0);
	const [ send, close ] = useApi('wss://echo.websocket.org');
	React.useEffect(() => {
		console.log('LENLEN mounint');
		return () => console.log('Cleaing up shit');
	}, []);
	return (
		<div>
			{/* <Navigation /> */}
			{/* <MainContent /> */}
			<ButtonGroup>
				<Button onClick={() => logStore.toggleOpen()} text="TOGGLE" />
				<Button onClick={() => close()} text="CLOSE" />
			</ButtonGroup>
			<H5 onClick={() => send('ASDAS')}>
				{JSON.stringify(logStore.logs)}
				{a}
			</H5>

			<LoggerV2 />
			{/* <Logger /> */}
		</div>
	);
}
