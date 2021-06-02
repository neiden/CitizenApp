import { comment } from './comment.js';
import { post } from './post.js';
import { user } from './user.js';

const routes = {
  Comment: comment,
  Post: post,
  User: user
};

export { routes }