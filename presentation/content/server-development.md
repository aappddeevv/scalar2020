# Sever Development

Let's use node express to develop the server. The server is based on
node and javascript and is quite popular and has many add ons. Its
not the fastest server but it is well proven and in a full stack
world you may may not have the luxury of choosing all the parts
of the technology stack that you want. The backend will have:

* node.js: Runtime environment.
* express: HTTP server framework.
* apollo graphql: The front end will be served by graphql.
* scala.js: Our "response" code will use scala.js to run
most of the effects.
* sqllite: Backend database
* connectors: Connectors to "downstream" services such as bert-as-service.

## Development Setup

We will use docker containers to develop the backend. We might use
docker for a few different reasons including:

* The dev dependencies do not match our local machine and
we do not want to change our local machine.
* Prod will run in a container. 
* Repeatable dev process.
* The main project that we are integrated into uses containers
and so must you.

To be completely independent of any "host" environment we should run
our editor in a container as well but for our workshop we will assume
that everyone has an editor installed along with a container service
like docker. Howevever, we are not assuming that we have node installed.
We must start with a basic container and build up to the layers.

You can use any editor we will not assume that we are using a specific
editor, such as visual studio code, which makes container development
easier. We will assume that you have the simplest editor, such as vi,
available.

I will use visual studio code since that's a popular choice but I
often use emacs as well. It's up to you.

One note, while sbt, which we will use for scala development,
is good at reproducing builds, we may still want to isolate
out the jdk dependency. We may be using sdk on the host for
jdk management but let's assume we want to lock down the jdk
dependency as well.

## Container Approach

There are a few different ways to create a container, some of which
lead to very slow development patterns. There are also different ways
to use a container environment.

A good approach is to use docker and isolate large dependencies
in their own immutable layer so that the layer does not need to
be rebuilt when you rebuild your container for whatever reason.

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

