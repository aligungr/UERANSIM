import * as React from 'react'
import { Hello } from './Blue'

import * as express from 'express';
import * as http from 'http';
import * as WebSocket from 'ws';

//const app = express();
//const server = http.createServer(app);
//const wss = new WebSocket.Server({ server });
//
//wss.on('connection', (ws: WebSocket) => {
//  ws.on('message', (message: string) => {
//    console.log('received: %s', message);
//    ws.send(`Hello, you sent -> ${message}`);
//  });
//  ws.send('Hi there, I am a WebSocket server');
//});

export const App = () => {
  React.useEffect(() => {
    document.body.className = 'bp3-dark'
  }, [])
  return <Hello />
}
