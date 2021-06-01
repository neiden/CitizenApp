import express from 'express';
import http from 'http';
import { normalize } from 'path';

import app from './app'
import models from './models';
import { sequelize, routes } from './routes';

/* get port */
const port = normalizePort(process.env.PORT);

/* create server */
const server = http.createServer


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