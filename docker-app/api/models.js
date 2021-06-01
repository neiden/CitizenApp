import pkg from 'sequelize';

const { Sequelize, DataTypes } = pkg;

const sequelize = new Sequelize(
  process.env.DATABASE,
  process.env.DATABASE_USER,
  process.env.DATABASE_PASSWORD,
  {
    dialect: 'postgres',
    port: process.env.DATABASE_PORT,
    host: process.env.DATABASE_HOST
  }
);

const models = {
  Comment: sequelize.define('comment', {
    body: {
      type: DataTypes.STRING
    }
  }),
  Post: sequelize.define('post', {
    title: {
      type: DataTypes.STRING
    },
    body: {
      type: DataTypes.STRING
    },
    long: {
      type: DataTypes.FLOAT
    },
    lat: {
      type: DataTypes.FLOAT
    },
  }),
  User: sequelize.define('user', {
    username: {
      type: DataTypes.STRING
    },
    password: {
      type: DataTypes.STRING
    },
    role: {
      type: DataTypes.FLOAT
    }
  })
};

models.Comment.belongsTo(models.User);
models.Comment.belongsTo(models.Post);
models.Comment.belongsToMany(models.User, { through: 'commentUpvotes' } );
models.Comment.belongsToMany(models.User, { through: 'commentDownvotes' } );

models.Post.belongsTo(models.User);
models.Post.belongsTo(models.User, { through: 'postUpvotes' } );
models.Post.belongsTo(models.User, { through: 'postDownvotes' } );

export { sequelize, models }