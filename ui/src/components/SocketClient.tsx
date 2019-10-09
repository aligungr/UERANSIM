

export class SocketClient {
  private ws: WebSocket

  constructor() {
    const _this = this;

    this.ws = new WebSocket("ws://localhost:5002");
    this.ws.onmessage = function(e: MessageEvent) { _this.onMessage(e); }
    this.ws.onopen = function(e: Event) { _this.onOpen(e); }
    this.ws.onclose = function(e: CloseEvent) { _this.onClose(e); }
    this.ws.onerror = function(e: Event) { _this.onError(e); }
  }

  private onMessage(e: MessageEvent) {
    console.log("onMessage, " + e);
  }

  private onOpen(e: Event) {
    console.log("onOpen, " + e);
  }

  private onClose(e: CloseEvent) {
    console.log("onClose, " + e);
  }

  private onError(e: Event) {
    console.log("onError, " + e);
  }

  send(data: string) {
    this.ws.send(data);
  }
}