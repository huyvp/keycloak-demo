# Setting Keycloak server
```sh
    # Mongo DB
    docker run -d --name keycloak 
    -p 8180:8080 
    -e KEYCLOAK_ADMIN=admin 
    -e KEYCLOAK_ADMIN_PASSWORD=admin 
    quay.io/keycloak/keycloak:25.0.0 start-dev
```
# Setting Web-app
- Create new project with vite
```sh

```

# Setting profile-service
- Install mongo db 
```sh
    # Mongo DB
    docker run -d --name mongodb
    -p 27017:27017 
    -e MONGO_INITDB_ROOT_USERNAME=admin 
    -e MONGO_INITDB_ROOT_PASSWORD=admin mongo:latest
```