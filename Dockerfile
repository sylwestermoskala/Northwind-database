FROM maven:3.5.4-jdk-8

RUN mkdir /usr/src/app
WORKDIR /usr/src/app

COPY . /usr/src/app
COPY ./src/main/resources/hibernate.docker.cfg.xml /usr/src/app/src/main/resources/hibernate.cfg.xml
RUN cat /usr/src/app/src/main/resources/hibernate.cfg.xml
VOLUME ['/usr/src/app/log']
CMD ["mvn","install"]
