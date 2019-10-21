import * as React from 'react';
import { Navigation } from './navigation';
import { MainContent } from './mainContent';
import { LoggerV2 } from './logger';

export function App() {
	return (
		<div>
			<Navigation />
			<MainContent />
			<LoggerV2 />
		</div>
	);
}
