import * as React from 'react';
import { Logger, logger } from './logger';
import { Navigation } from './navigation';
import { MainContent } from './mainContent';
import { useThemeStore } from '../basis/stores';
import { useLogStore } from '../stores';
import { LoggerV2 } from './loggerv2';
import { Button, H5 } from '@blueprintjs/core';

export function App() {
	const logStore = useLogStore();

	return (
		<div>
			{/* <Navigation /> */}
			{/* <MainContent /> */}
			<Button onClick={() => logStore.toggleOpen()} text="TOGGLE" />
			<H5 onClick={() => logStore.getTaggedLogger('ASD').debug(Math.random().toFixed(3))}>
				{JSON.stringify(logStore.logs)}
			</H5>
			<LoggerV2 />
			{/* <Logger /> */}
		</div>
	);
}
