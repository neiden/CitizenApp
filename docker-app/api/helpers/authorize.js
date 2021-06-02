import jwt from 'jsonwebtoken';

export default (req, res, next) => {
  /* Check if the authorization header is set */
  if (req.get('Authorization')) {
    /* Slice off 'bearer ' */
    var token = req.get('Authorization').slice(7);
    /* Check accuracy of the token */
    if (jwt.verify(token, process.env.API_SECRET)) {
      /* If we're authorized, then continue to the callback function. */
      // console.log(JSON.parse(jwt.decode(token).data).id);
      req.body.UserId = JSON.parse(jwt.decode(token).data).id
      next();
    } else {
      return res.status(401).json({ message: 'Unauthorized' });
    }
  } else {
    return res.status(401).json({ message: 'Unauthorized' });
  }
};