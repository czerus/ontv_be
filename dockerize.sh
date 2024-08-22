set -e

mvn clean package
cp target/OnTv-1.0-SNAPSHOT-spring-boot.jar docker/
cd docker
docker build -t "ontv:latest" .
rm -rf OnTv-1.0-SNAPSHOT-spring-boot.jar