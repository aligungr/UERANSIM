import * as React from 'react';
import { useLogStore, LogType } from '../stores/log';
import { Collapse, Pre, ButtonGroup, Tooltip, Button, Divider, Callout, Icon, H6 } from '@blueprintjs/core';
import { Constants } from '../basis/constants';

export const LoggerV2: React.FC = () => {
	const [ logs, clearLogs, isOpen, toggleOpen ] = useLogStore((s) => [ s.logs, s.clearLogs, s.isOpen, s.toggleOpen ]);
	const [ autoScroll, setAutoScroll ] = React.useState(false);
	const [ isDark, _ ] = React.useState(false);
	const ref = React.useRef<HTMLDivElement>();

	React.useEffect(
		() => {
			if (autoScroll) ref.current.scrollTop = ref.current.scrollHeight;
		},
		[ logs, autoScroll ]
	);

	const getColor = React.useCallback(
		(type: LogType) => {
			switch (type) {
				case LogType.DEBUG:
					return isDark ? Constants.COLOR_LIGHT_LOG_NORMAL : Constants.COLOR_DARK_LOG_NORMAL;
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
			<Callout style={{ display: isOpen ? 'none' : 'flex' }}>
				<Icon
					color="white"
					icon="chevron-up"
					onClick={toggleOpen}
					style={{ cursor: 'pointer', paddingRight: '15px' }}
				/>
				<H6> {logs.length > 0 && logs[logs.length - 1].entry} </H6>
			</Callout>

			<Collapse keepChildrenMounted={true} isOpen={isOpen}>
				<Pre
					style={{
						maxHeight: '200px',
						minHeight: '200px',
						display: 'flex',
						padding: '4px'
					}}
				>
					<ButtonGroup minimal vertical={true}>
						<Tooltip content="Minimize console">
							<Button icon="chevron-down" onClick={toggleOpen} />
						</Tooltip>
						<Tooltip content={'Clear Console'}>
							<Button icon="trash" onClick={(e: any) => clearLogs()} />
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
						{logs.map((log, i) => {
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
