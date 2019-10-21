import * as React from 'react';
import { Collapse, Pre, ButtonGroup, Tooltip, Button, Divider, Callout, Icon, H6 } from '@blueprintjs/core';
import { Constants } from '../basis/constants';
import { useConsoleStore } from '../stores/consoleStore';

export enum LogType {
	'INFO',
	'SUCCESS',
	'WARNING',
	'ERROR',
}

export type LogEntry = {
	entry: string;
	type: LogType;
	id: number;
};

export let logger: LoggerObject = {
	append: (text: string, logType: LogType) => notInitYet(),
	clear: () => notInitYet(),
	info: (text: string, tag: string | null = null) => notInitYet(),
	error: (text: string, tag: string | null = null) => notInitYet(),
	success: (text: string, tag: string | null = null) => notInitYet(),
	warning: (text: string, tag: string | null = null) => notInitYet(),
}

function notInitYet() {
	//alert('Error: logger is not ready')
	//document.documentElement.innerText = 'Error: logger is not ready'
}

export type LoggerObject = {
	append: (entry: string, logType: LogType) => void;
	clear: () => void;
	success: (entry: string, tag: string | null) => void;
	warning: (entry: string, tag: string | null) => void;
	error: (entry: string, tag: string | null) => void;
	info: (entry: string, tag: string | null) => void;
};

export const LoggerV2: React.FC = () => {
	const consoleStore = useConsoleStore()

	const [ autoScroll, setAutoScroll ] = React.useState(false);
	const [ isDark, _ ] = React.useState(false);
	const ref = React.useRef<HTMLDivElement>();

	React.useEffect(
		() => {
			if (autoScroll) ref.current.scrollTop = ref.current.scrollHeight;
		},
		[ consoleStore.logs, autoScroll ]
	);

	const getColor = React.useCallback(
		(type: LogType) => {
			switch (type) {
				case LogType.INFO:
					return isDark ? Constants.COLOR_LIGHT_LOG_INFO : Constants.COLOR_DARK_LOG_INFO;
				case LogType.SUCCESS:
					return isDark ? Constants.COLOR_LIGHT_LOG_SUCCESS : Constants.COLOR_DARK_LOG_SUCCESS;
				case LogType.WARNING:
					return isDark ? Constants.COLOR_LIGHT_LOG_WARNING : Constants.COLOR_DARK_LOG_WARNING;
				case LogType.ERROR:
					return isDark ? Constants.COLOR_LIGHT_LOG_ERROR : Constants.COLOR_DARK_LOG_ERROR;
			}
		},
		[ isDark ]
	);

	return (
		<footer className={'console-footer'}>
			<Callout style={{ display: consoleStore.isOpen ? 'none' : 'flex' }}>
				<Icon
					color="white"
					icon="chevron-up"
					onClick={consoleStore.toggleOpen}
					style={{ cursor: 'pointer', paddingRight: '15px' }}
				/>
				<H6> {consoleStore.logs.length > 0 && consoleStore.logs[consoleStore.logs.length - 1].entry} </H6>
			</Callout>

			<Collapse keepChildrenMounted={true} isOpen={consoleStore.isOpen}>
				<Pre
					style={{
						maxHeight: '200px',
						minHeight: '200px',
						display: 'flex',
						padding: '4px'
					}}
				>
					<ButtonGroup minimal={true} vertical={true}>
						<Tooltip content="Minimize console">
							<Button icon="chevron-down" onClick={consoleStore.toggleOpen} />
						</Tooltip>
						<Tooltip content={'Clear Console'}>
							<Button icon="trash" onClick={(e: any) => consoleStore.clear()} />
						</Tooltip>
						<Tooltip content={`${autoScroll ? 'Disable' : 'Enable'} Auto Scroll to Bottom`}>
							<Button
								icon="automatic-updates"
								active={autoScroll}
								onClick={() => setAutoScroll(!autoScroll)}
							/>
						</Tooltip>
					</ButtonGroup>
					<Divider />
					<div
						ref={ref}
						id={'bp-console-content'}
						style={{
							overflow: 'auto',
							width: '100%',
							padding: '8px',
							marginLeft: '6px'
						}}
					>
						{consoleStore.logs.map((log, i) => {
							return (
								<div
									key={'' + i}
									style={{
										color: getColor(log.type)
									}}
								>
									{log.entry}
								</div>
							);
						})}
						<br />
					</div>
				</Pre>
			</Collapse>
		</footer>
	);
};
