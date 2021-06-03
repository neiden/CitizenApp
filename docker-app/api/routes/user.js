import express from 'express';
import { models } from '../models.js';
import { createToken } from '../helpers/authenticate.js';
import authorize from '../helpers/authorize.js';

const user = express.Router();

user.get('/', authorize, (req, res) => {
  models.User.findAll()
    .then((users) => {
      return res.status(200).json(users);
    })
    .catch((err) => {
      console.error(err);
      return res.status(400).json({ message: 'Bad Request' });
    });
});

/* Login */
user.post('/login', (req, res) => {
  const query = {};
  query.where = {};
  if (req.body.username && req.body.password) {
    query.where.username = req.body.username;
    query.where.password = req.body.password;
  }
  else {
    console.error("Error in body: " + req.body);
    return res.status(400).json({ message: 'Bad Request' });
  }
  /* Execute query */
  models.User.findOne(query)
    /* Successful find */
    .then((user) => {
      if (user) {
        user.dataValues.token = createToken(user.id);
        return res.status(200).json(user);
      }
      else {
        console.error("Error in sql");
        return res.status(400).json({ message: 'Bad Request' });
      }
    })
    /* Handle Errors */
    .catch((err) => {
      console.error(err);
      return res.status(400).json({ message: 'Bad Request' });
    });
});

/* Register */
user.post('/', (req, res) => {
  models.User.create(req.body)
    /* Successful insert */
    .then((user) => {
      user.dataValues.token = createToken(user.id);
      return res.status(201).json(user);
    })
    /* Handle Errors */
    .catch((err) => {
      console.error(err);
      return res.status(400).json({ message: 'Bad Request' });
    });
});

export { user }