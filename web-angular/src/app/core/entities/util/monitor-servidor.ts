export class MonitorServidor {

  serverId: number;
  name: string;
  ip: string;
  port: number;
  active: boolean;
  monitoring: boolean;
  started: boolean;
  connectivity: boolean;
  lastCheck: number;
  protocoloStr: string;
  lastCheckMessage: string;

  constructor() { }

  public getDiff(): string {
    if (!this.lastCheck) {
      return '';
    } else {
      const now = Date.now();
      let diff = (now - this.lastCheck) / 1000;
      let result = '';
      if (diff < 359) {
        result = diff + ' Segundos';
      } else {
        diff = Math.floor(diff / 60);
        if (diff < 239) {
          result = diff + ' Minutos';
        } else {
          diff = Math.floor(diff / 60);
          if (diff < 95) {
            result = diff + ' Horas';
          } else {
            diff = Math.floor(diff / 24);
            if (diff < 1824) {
              result = diff + ' Dias';
            } else {
              diff = Math.floor(diff / 365);
              result = diff + ' Anos';
            }
          }
        }
      }
      return result;
    }
  }
}
