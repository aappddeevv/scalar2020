---
title: "Server Development"
metaTitle: "Server Development"
metaDescription: "Server Development"
---

Let's use node express to develop the server. The server is based on node and javascript and is quite popular and has many add ons. Its not the fastest server but it is well proven and in a full stack world you may may not have the luxury of choosing all the parts of the technology stack that you want. The backend will have:

* node.js: Runtime environment.
* express: HTTP server framework.
* apollo graphql: The front end will be served by graphql.
* scala.js: Our "response" code will use scala.js to run
most of the effects.
* sqllite: Backend database
* connectors: Connectors to "downstream" services such as bert-as-service.

# Development Setup

We will use containers to develop the backend. We might use docker for a few different reasons including:

* The dev dependencies do not match our local machine and
we do not want to change our local machine.
* Prod will run in a container. 
* Repeatable dev process.
* The main project that we are integrated into uses containers
and so must you.

To be completely independent of any "host" environment we should run your editor in a container as well but for our workshop we will assume that everyone has an editor installed along with a container service like docker. However, we are not assuming that we have node installed. We must start with a basic container and build up to the layers.

You can use any editor we will not assume that we are using a specific editor, such as visual studio code, which makes container development easier. We will assume that you have the simplest editor, such as vi, available.

I will use visual studio code since that's a popular choice but I often use emacs as well. It's up to you.

One note, while sbt, which we will use for scala development, is good at reproducing builds, we may still want to isolate out the jdk dependency. We may be using sdk on the host for jdk management but let's assume we want to lock down the jdk dependency as well.

# Container Approach

There are a few different ways to create a container, some of which lead to very slow development patterns. There are also different ways to use a container environment.

A good approach is to use docker and isolate large dependencies in their own immutable layer so that the layer does not need to be rebuilt when you rebuild your container for whatever reason.

Here's our approach to using a container for development work:

* Host
  * Editor
  * Development files
  * git
  * browser e.g. chrome/firefox/brave
  * filesystem content shared with containers:
    * Build artifacts
    * npm modules (.e.g. node_modules)
* Container
  * node base runtime
  * jdk
  * sbt
  * scalac

If your IDE/editor running on the Host supports debugging, we would
want to connect to the processes running in the container.

Notice that in this case, we are using a shared filesystem on the
host and any temporary development files will also be directly
visible on the even though they are generated from processes
running in the container. Obviously, for production use,
we would use a different container approach and all of the dependencies
would need to be in the container. You may choose this approach
if your prod environment must be minimized and you do not want
to install npm "dev" dependencies into the container because
they would take up additional space, etc. Most tutorials you see on
using containers and node together will install everything
regardless of prod or dev usage.

Changes made in a container are persisted (if you do *not* use
--rm to run it) and you can start a stopped container to see all
of the changes. However, in practice for development
I found that I typically forget to push changes to my repo or save
them.

# Explore Dev Containers

Lets install a dev image and create a container. You can review 
images that you like at https://hub.docker.com and find a base
image that you like. You may also find an image that has everything
you want in it. However, unless you are running podman with rootless
pods, be aware that if you run your docker images with elevated
privileges, a container may be malicious and harm your system.

You can look at and compare several images:

* ubuntu:latest
* centos:latest
* fedora:latest

You can pull the image then start it or pull it independently. Here's the pull.

```sh
docker pull ubuntu:latest
```

If you just run it, it will pull the image if it is not local already. Most of the images include a shell that runs by default:

```sh
docker run -it ubuntu:latest
```

If an images did not set a default command (CMD) to run when the container
starts you can use:

```sh
docker run -it ubuntu:latest sh
```

You can also start a container with some software already installed. Most
major software platforms have pre-built images for you to try out:

```sh
# choose any version to try it out e.g. node:alpine or node:latest 
docker run -it node:alpine
```

This drops you into a node shell. To run and start a shell:

```sh
docker run -it node:alpine sh
```

You can see all the various available at the hub registry https://hub.docker.com/_/node
and browse the tags. You will see slim version, alpine versions and others mosty with different bases. The hub also shows you the Dockerfile so you can copy parts of it into a new Dockerfile. Some of the installs can be complex and there are a few tricks to building the images efficiently.

If you search for both node and java, you may find `timbru31/java-node:latest` which has both parts already installed. Search for openjdk, oraclejdk and others to see what's available.

If you are building a development image, you may have a preference
for the base that is used to build the platform level image as the base may
cause restrictions to occur. For example, the alpine images do not use glibc
so anything requiring glibc may have difficulty.

# Create Dev Container Interactively

We will use a base node container then add java to it. You can build a dev image 2 ways:

* Interactively: Start a container and then issues the commands interactively.
  * Once you are finished, "docker commit" the container to save your changes as an image.
  * Stop the container. Never delete it. Restart it when you want to do more work.
* Create a recipe via a Dockerfile.

I always create it interactively and step by step write down instructions in a Dockerfile once I have it right.

Here's the interactive version. After the first command, the commands are executed in the container shell.

```sh
$ docker run -it node:alpine sh

# apk add --no-cache openjdk11 curl ncurses

# curl -Lo coursier https://git.io/coursier-cli &&
    chmod +x coursier && mv coursier /usr/local/bin/cs

# export PATH=/root/.local/share/coursier/bin:$PATH

# cs install ammonite sbt-launcher mill
```

Once created we stop and star the container (don't delete it!) and all the software stays installed. We could also run `docker commit <containerid>` to commit. You can `Ctrl-P Ctrl-Q` out of the shell to exit the container. If its still running you can always start a shell in the running container via `docker exec -it <containerid> sh`.

# Create Dev Container Using Recipe

Create a file `Dockerfile` which is based on YAML syntax:

```dockerfile
# name the base layer so we can use this as a base in a multi-stage build later
FROM node:alpine as builder

RUN apk add --no-cache openjdk11 curl ncurses

RUN curl -Lo coursier https://git.io/coursier-cli && \
    chmod +x coursier && \
    mv coursier /usr/local/bin/cs && \
    cs install ammonite sbt-launcher mill && \
    echo "export PATH=/root/.local/share/coursier/bin:$PATH" >> /etc/profile 

ENV ENV="/etc/profile"

CMD ["/bin/sh"]
```

Run `docker build -t dev -f Dockerfile` to build the image. Your image is listed as "dev" from running `docker images`. The use of `as builder` means that this layer can be referened by other layers if we extend Dockerfile. That will be important in later steps when a multi-stage build is more helpful.

# Try It

```sh
cd modules/module1/10_containers

docker build -t dev -f Dockerfile

docker run -it --rm dev:latest
```

