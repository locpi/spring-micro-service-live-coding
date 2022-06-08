docker build  --build-arg file=eureka.jar -t ducks/infra-eureka:latest -f Dockerfile .
docker build  --build-arg file=zuul.jar -t ducks/infra-gateway:latest -f Dockerfile .
