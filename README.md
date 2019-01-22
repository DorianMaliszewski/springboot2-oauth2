# springboot2-oauth2

OAuth2 implementation : Authorization Server, Ressource Server and React integration with Spring boot 2 (database : MariaDB)

## Plug and play

- Start a Mariadb or Mysql Database, launch the script in oauth2-server/src/main/resources/schema.sql
- Create a database named test_resource
- Launch oauth2-server :
  - Go into the oauth2-server directory
  - Launch `./mvnw spring-boot:run`
- Launch oauth2-resource :
  - Go into the oauth2-resource directory
  - Launch `./mvnw spring-boot:run`
- Launch the client :
  - Go into react app directory
  - Launch `yarn start` or `npm start`

## Example users

- Example : username - password

- Administrator : admin - admin
- Super Administrator : superadmin - superadmin
- User : user - user
