import express from 'express';

const comment = express.Router();

comment.get('/', async (req, res) => {
  return res.status(200).json({ "Message" : "OK" });
});

export { comment }