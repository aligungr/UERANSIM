import { Alignment, AnchorButton, Navbar, NavbarDivider, NavbarGroup, NavbarHeading, Tooltip } from '@blueprintjs/core';
import * as React from 'react';
import { useLoggerStore } from '../stores/loggerStore';
import { useAppStore } from '../stores/appStore';
import { useThemeStore } from '../stores/themeStore';

export function Navigation(props: any) {
	const themeStore = useThemeStore();
	const appStore = useAppStore();
	const loggerStore = useLoggerStore();

	return (
		<Navbar>
			<NavbarGroup align={Alignment.LEFT}>
				<NavbarHeading>{appStore.appName}</NavbarHeading>
				<NavbarDivider />
				<Tooltip content={`${loggerStore.isOpen ? 'Hide' : 'Show'} Console`}>
					<AnchorButton
						minimal={true}
						rightIcon="console"
						onClick={() => loggerStore.toggleOpen()}
						active={loggerStore.isOpen}
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
