import express from 'express';

const comment = express.Router();

comment.get('/', (req, res) => {
  return res.status(200).json({ "Message" : "OK" });
});

export { comment }