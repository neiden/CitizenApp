import express from 'express';

import { models } from '../models.js';
import authorize from '../helpers/authorize.js';

const comment = express.Router();

/* get all comments */
comment.get('/', authorize, (req, res) => {
  const query = {
    include: [
      {
        model: models.User
      }
    ]
  }
  models.Comment.findAll(query)
    .then((posts) => {
      return res.status(200).json(posts);
    })
    .catch((err) => {
      console.error(err);
      return res.status(400).json({ message: 'Bad Request' });
    });
});

/* get all comments associated with a post */
comment.get('/:id', authorize, (req, res) => {
  const query = {
    where: {
      PostId: req.params.id
    },
    include: [
      {
        model: models.User
      }
    ]
  }
  models.Comment.findAll(query)
    .then((posts) => {
      return res.status(200).json(posts);
    })
    .catch((err) => {
      console.error(err);
      return res.status(400).json({ message: 'Bad Request' });
    });
});

/* Create comment */
comment.post('/:id', authorize, (req, res) => {
  req.body.PostId = req.params.id;
  let query = {
    include: [
      {
        model: models.User
      }
    ]
  }
  models.Comment.create(req.body, query)
    .then(async (comment) => {
      let newComment = await models.Comment.findByPk(comment.id, query);
      return res.status(201).json(newComment);
    })
    .catch((err) => {
      console.error(err);
      return res.status(400).json({ message: 'Bad Request' });
    });
});

/* upvote a comment */
comment.post('/upvote/:id', authorize, (req, res) => {
  req.body.PostId = req.params.id;
  models.CommentUpvotes.create(req.body)
  .then((commentuv) => {
    return res.status(201).json(commentuv);
  })
  .catch((err) => {
    console.error(err);
    return res.status(400).json({ message: 'Bad Request' });
  });
});

/* downvote a pcomment */
comment.post('/downvote/:id', authorize, (req, res) => {
  req.body.PostId = req.params.id;
  models.CommentDownvotes.create(req.body)
  .then((commentdv) => {
    return res.status(201).json(commentdv);
  })
  .catch((err) => {
    console.error(err);
    return res.status(400).json({ message: 'Bad Request' });
  });
});

export { comment }