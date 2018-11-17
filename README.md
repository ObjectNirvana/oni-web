# oni-web
Scala project for main objectnirvana.com website

# SBT build notes

generate test coverage report:

backend/scct:test

## This project uses these tools:

changing to 2.12

- Scala 2.11
- Scala.js
- Udash
- Scala.js jQuery wrapper
- scalatags
- scalacss
- Mongo DB
- Mongo DB Casbah
- Salat
- RxScala
- Akka
- Jetty

setup discourse

http://stackoverflow.com/questions/12720967/how-to-change-postgresql-user-password
ALTER USER "user_name" WITH PASSWORD 'new_password';

## SBT tools:

- sbt-scct
- sbt-dependency-graph

To have launchd start mongodb now and restart at login:
  brew services start mongodb
Or, if you don't want/need a background service you can just run:
  mongod --config /usr/local/etc/mongod.conf
==> Summary
🍺  /usr/local/Cellar/mongodb/3.4.7: 19 files, 281.9MB
