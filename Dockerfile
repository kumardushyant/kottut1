FROM ubuntu:22.04

ENV JAVA_HOME /opt/jdk
ENV PATH $JAVA_HOME/bin:$PATH

RUN apt update -y && apt upgrade -y && apt install curl -y && \
    mkdir -p /project 
COPY openjdk-17-crac+5_linux-x64.tar.gz $JAVA_HOME/openjdk.tar.gz
COPY build/libs/tut.dushyant-0.0.1-SNAPSHOT.jar /project/app.jar
COPY scripts/restore.sh /project/restore.sh
COPY scripts/checkpoint.sh /project/checkpoint.sh
RUN curl -L https://github.com/CRaC/openjdk-builds/releases/download/17-crac%2B5/openjdk-17-crac+5_linux-x64.tar.gz -o ${JAVA_HOME}/openjdk.tar.gz && \
    tar --extract --file $JAVA_HOME/openjdk.tar.gz --directory "$JAVA_HOME" --strip-components 1 && \
    rm $JAVA_HOME/openjdk.tar.gz && chmod +x /project/restore.sh && chmod +x /project/checkpoint.sh

WORKDIR /project
ENTRYPOINT /project/checkpoint.sh
