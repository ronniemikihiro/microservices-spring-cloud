import { NgxLoggerLevel } from 'ngx-logger';

export const environment = {
  production: true,
  logLevel: NgxLoggerLevel.OFF,
  serverLogLevel: NgxLoggerLevel.ERROR,
  apiURLBase: 'http://gateway-container:8080',
  tokenUrl: '/auth/token',
  refreshTokenUrl: '/auth/refreshToken'
};
