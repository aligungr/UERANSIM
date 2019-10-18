import * as React from 'react'
import { useLogStore } from "../stores"
import { Collapse, Pre, ButtonGroup, Tooltip, Button, Divider } from '@blueprintjs/core'

export const LoggerV2: React.FC = () => {
    const [logs, logger] = useLogStore(s => [s.logs, s.getTaggedLogger("LELEY")])
    const [autoScroll, setAutoScroll] = React.useState(false)
    const ref = React.useRef<HTMLDivElement>()

    // const getColor = React.useCallback((type: "WARNING" | "DEBUG" | "INFO" | "ERROR") => {
    //     switch (type) {
    //         case LogType.NORMAL:
    //             return isDark ? Constants.COLOR_LIGHT_LOG_NORMAL : Constants.COLOR_DARK_LOG_NORMAL
    //         case LogType.SUCCESS:
    //             return isDark ? Constants.COLOR_LIGHT_LOG_SUCCESS : Constants.COLOR_DARK_LOG_SUCCESS
    //         case LogType.WARNING:
    //             return isDark ? Constants.COLOR_LIGHT_LOG_WARNING : Constants.COLOR_DARK_LOG_WARNING
    //         case LogType.ERROR:
    //             return isDark ? Constants.COLOR_LIGHT_LOG_ERROR : Constants.COLOR_DARK_LOG_ERROR
    //     }
    // }, [isDark])



    return (
        <footer className={'console-footer'}>
            {JSON.stringify(logs)}
            <Button onClick={() => logger.error(Math.random().toFixed(3).toString())}>CLICK</Button>
        </footer>
    )
}   