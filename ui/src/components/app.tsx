import * as React from 'react';
import { Navigation } from './navigation';
import { MainContent } from './mainContent';
import { LoggerV2 } from './loggerv2';
import { useLogStore } from '../stores/log';

export function App() {
	const logStore = useLogStore();
	return (
		<div>
			{ <Navigation /> }
			{ <MainContent /> }
			{
				/*
				<ButtonGroup>
					<Button onClick={() => logStore.toggleOpen()} text="TOGGLE" />
					<Button onClick={() => close()} text="CLOSE" />
				</ButtonGroup>
					<H5 onClick={() => send('ASDAS')}>
				{JSON.stringify(logStore.logs)}
				{a}
			</H5>
			*/
			}
			<LoggerV2 />
			{/* <Logger /> */}
		</div>
	);
}
