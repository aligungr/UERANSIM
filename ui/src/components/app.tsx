import * as React from 'react';
import { Navigation } from './navigation';
import { MainContent } from './mainContent';
import { LoggerV2 } from './loggerv2';
import { useLogStore } from '../stores/log';

export function App() {
	const logStore = useLogStore();
	return (
		<div>
			<Navigation />
			<MainContent />
			<LoggerV2 />
			{/* <Logger /> */}
		</div>
	);
}
