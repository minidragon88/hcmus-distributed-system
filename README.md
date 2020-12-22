# hcmus-distributed-system
Project for demonstrate distributed system

## master-service
```
./gradlew clean build bootRun -test --args='--server.port=8081'
```

## worker-service
```
./gradlew clean build bootRun -x test --args='--server.port=8082'
```
