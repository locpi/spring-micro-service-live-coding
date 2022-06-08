source deploy/.env
docker build --build-arg file=tips/orders --build-arg jarName=orders --build-arg version=${VERSION_ORDERS} -t ducks/orders:${VERSION_ORDERS} .
