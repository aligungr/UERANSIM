import { Alignment, AnchorButton, Navbar, NavbarDivider, NavbarGroup, NavbarHeading, Tooltip } from '@blueprintjs/core';
import * as React from 'react';
import { useThemeStore, useAppStore, useConsoleStore } from '../basis/stores';

export function Navigation(props: any) {
	const themeStore = useThemeStore();
	const appStore = useAppStore();
	const consoleStore = useConsoleStore();

	return (
		<Navbar>
			<NavbarGroup align={Alignment.LEFT}>
				<NavbarHeading>{appStore.appName}</NavbarHeading>
				<NavbarDivider />
				<Tooltip content={`${consoleStore.isOpen ? 'Hide' : 'Show'} Console`}>
					<AnchorButton
						minimal={true}
						rightIcon="console"
						onClick={() => consoleStore.toggleOpen()}
						active={consoleStore.isOpen}
					/>
				</Tooltip>
				<Tooltip content={`Switch to ${themeStore.isDark ? 'Light' : 'Dark'} Theme`}>
					<AnchorButton
						minimal={true}
						rightIcon={themeStore.isDark ? 'flash' : 'moon'}
						onClick={() => themeStore.toggleTheme()}
					/>
				</Tooltip>
			</NavbarGroup>
		</Navbar>
	);
}
