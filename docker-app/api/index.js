import express from 'express';
import http from 'http';
import { normalize } from 'path';

import app from './app'
import { models, sequelize } from './models';
import routes from './routes';

/* get port */
const port = normalizePort(process.env.PORT);

/* create server */
const server = http.createServer(app);

/* sync db */
sequelize.sync().then(async () => {
  server.listen(port);
  server.on('error', (error) => {
    switch (error.code) {
      case 'EACCES':
        console.error(`${bind} requires elevated privileges`);
        process.exit(1);
        break;
      case 'EADDRINUSE':
        console.error(`${bind} is already in use`);
        process.exit(1);
        break;
      default:
        throw error;
    }
  });
  server.on('listen', () => {
    const addr = server.address();
    const bind = typeof addr === 'string'
    ? `pipe ${addr}`
    : `port ${addr.port}`;
    console.log(`Listening on ${bind}`);
  });
});

/**
 * Normalize a port into a number, string, or false.
 */

function normalizePort(val) {
  const portNumber = parseInt(val, 10);

  if (isNaN(portNumber)) {
    // named pipe
    return val;
  }

  if (portNumber >= 0) {
    // port number
    return portNumber;
  }

  return false;
}