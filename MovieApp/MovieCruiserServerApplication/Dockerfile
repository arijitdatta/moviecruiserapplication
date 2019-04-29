FROM java:8-jre
WORKDIR usr/src
ENV MYSQL_DATABASE=moviedb
ENV MYSQL_USER=root
ENV MYSQL_PASSWORD=root
ENV MYSQL_CI_URL=jdbc:mysql://localhost:3306/moviedb
ADD ./target/moviecruiserserverapplication-0.0.1-SNAPSHOT.jar /usr/src/moviecruiserserverapplication-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "moviecruiserserverapplication-0.0.1-SNAPSHOT.jar"]
