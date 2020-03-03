FROM node:alpine as builder

RUN apk add --no-cache openjdk11 curl ncurses

RUN curl -Lo coursier https://git.io/coursier-cli && \
    chmod +x coursier && \
    mv coursier /usr/local/bin/cs && \
    cs install ammonite sbt-launcher mill && \
    echo "export PATH=/root/.local/share/coursier/bin:$PATH" >> /etc/profile 

ENV ENV="/etc/profile"

CMD ["/bin/sh"]
