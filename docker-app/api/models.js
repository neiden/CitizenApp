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
  Comment: sequelize.define('Comment', {
    body: {
      type: DataTypes.STRING
    }
  }),
  Post: sequelize.define('Post', {
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
  User: sequelize.define('User', {
    username: {
      type: DataTypes.STRING,
      allowNull: false,
      unique: true
    },
    password: {
      type: DataTypes.STRING,
      allowNull: false
    },
    role: {
      type: DataTypes.FLOAT
    }
  },
  {
    defaultScope: {
      attributes: { exclude: ['password'] }
    }
  }),
  PostUpvotes: sequelize.define('PostUpvotes', {}),
  PostDownvotes: sequelize.define('PostDownvotes', {}),
  CommentUpvotes: sequelize.define('CommentUpvotes', {}),
  CommentDownvotes: sequelize.define('CommentDownvotes', {})
}

models.Comment.belongsTo(models.User);
models.Comment.belongsTo(models.Post);
models.Comment.belongsToMany(models.User, { through: models.CommentUpvotes } );
models.Comment.belongsToMany(models.User, { through: models.CommentDownvotes } );

models.Post.belongsTo(models.User);
models.Post.belongsToMany(models.User, { through: models.PostUpvotes } );
models.Post.belongsToMany(models.User, { through: models.PostDownvotes } );

models.User.hasMany(models.Post);
models.User.hasMany(models.Comment);
models.User.belongsToMany(models.Post, { through: models.PostUpvotes } );
models.User.belongsToMany(models.Post, { through: models.PostDownvotes } );
models.User.belongsToMany(models.Comment, { through: models.CommentUpvotes } );
models.User.belongsToMany(models.Comment, { through: models.CommentDownvotes } );

export { sequelize, models }