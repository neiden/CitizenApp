import express from 'express';
import { models } from '../models.js';
import authorize from '../helpers/authorize.js';

const post = express.Router();

/* get all posts */
post.get('/', authorize, (req, res) => {
  const query = {
    include: [
      {
        model: models.User
      },
      {
        model: models.User,
        association: models.PostDownvotes
      }
    ]
  }
  models.Post.findAll(query)
    .then((posts) => {
      
      return res.status(200).json(posts);
    })
    .catch((err) => {
      console.error(err);
      return res.status(400).json({ message: 'Bad Request' });
    });
});

/* Create post */
post.post('/', authorize, (req, res) => {
  models.Post.create(req.body)
    .then((post) => {
      return res.status(201).json(post);
    })
    .catch((err) => {
      console.error(err);
      return res.status(400).json({ message: 'Bad Request' });
    });
});

/* upvote a post */
post.post('/upvote/:id', authorize, (req, res) => {
  req.body.PostId = req.params.id;
  models.PostUpvotes.create(req.body)
  .then((postuv) => {
    return res.status(201).json(postuv);
  })
  .catch((err) => {
    console.error(err);
    return res.status(400).json({ message: 'Bad Request' });
  });
});

/* downvote a post */
post.post('/downvote/:id', authorize, (req, res) => {
  req.body.PostId = req.params.id;
  models.PostDownvotes.create(req.body)
  .then((postdv) => {
    return res.status(201).json(postdv);
  })
  .catch((err) => {
    console.error(err);
    return res.status(400).json({ message: 'Bad Request' });
  });
});

export { post }