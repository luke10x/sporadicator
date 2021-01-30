FROM maven:3-openjdk-11
COPY ./pom.xml /h2sproxy/pom.xml
WORKDIR /h2sproxy
RUN mvn dependency:resolve
