import { NgxLoggerLevel } from 'ngx-logger';

export const environment = {
    production: true,
    logLevel: NgxLoggerLevel.OFF,
    serverLogLevel: NgxLoggerLevel.ERROR,
    //apiURLBase: 'http://localhost:8080',
    apiURLBase: 'http://10.222.31.188:8080',
    tokenUrl: '/auth/token',
    refreshTokenUrl: '/auth/refreshToken'
  };