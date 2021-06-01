import Sequelize from 'sequelize';

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

models.Comment.associate = (models) => {
  Comment.belongsTo(models.User);
  Comment.belongsTo(models.Post);
};

module.exports = { sequelize, models }