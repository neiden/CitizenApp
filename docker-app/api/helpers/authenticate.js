import jwt from 'jsonwebtoken';

function createToken(userId) {
  var token = jwt.sign({
    exp: Math.floor(Date.now() / 1000) + (60 * 60),
    data: JSON.stringify({ id: userId })
  }, process.env.API_SECRET);
  return token;
}

export { createToken }