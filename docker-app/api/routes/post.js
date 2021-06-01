import express from 'express';
import { models } from '../models.js';

const post = express.Router();

post.get('/', async (req, res) => {
  models.Post.findAll()
    .then((posts) => {
      return res.status(200).json(posts);
    })
    .catch((err) => {
      return res.status(400).json({ message: 'Bad Request' });
    });
});

post.post('/', async (req, res) => {
  models.Post.create(req.body)
    .then((post) => {
      return res.status(201).json(post);
    })
    .catch((err) => {
      return res.status(400).json({ message: 'Bad Request' });
    });
});

export { post }