# hcmus-distributed-system
Project for demonstrate distributed system

## master-service
```
./gradlew clean build bootRun -x test --args='--server.port=8081'
```

## worker-service
```
./gradlew clean build bootRun -x test --args='--server.port=8082'
```

## h2 console
```
http://localhost:8081/h2-console

username = sa
password empty
```

