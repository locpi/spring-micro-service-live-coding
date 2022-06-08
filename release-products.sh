source deploy/.env
#mvn package -f tips/products/pom.xml  IF mvn is install
docker build --build-arg file=tips/products --build-arg jarName=products --build-arg version=${VERSION_PRODUCTS} -t ducks/products:${VERSION_PRODUCTS} .
