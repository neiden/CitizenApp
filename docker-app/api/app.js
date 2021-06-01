import { routes } from './routes/index.js';
import express from 'express';
import createError from 'http-errors';

const app = express();
app.use(express.json());
app.use('/comment', routes.Comment);
app.use('/post', routes.Post);
app.use('/user', routes.User);

app.use((err, req, next) => {
  next(createError(404));
});

// error handler
app.use((err, req, res) => {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};

  // render the error page
  res.status(err.status || 500);
  res.render('error');
});

export { app }