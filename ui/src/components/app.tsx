import * as React from 'react';
import { Navigation } from './navigation';
import { MainContent } from './mainContent';
import { Logger } from './logger';

export function App() {
	return (
		<div>
			<Navigation />
			<MainContent />
			<Logger />
		</div>
	);
}
