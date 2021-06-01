import express from 'express';

const user = express.Router();

user.get('/', async (req, res) => {
  return res.status(200).json({ "Message" : "OK" });
});

export { user }