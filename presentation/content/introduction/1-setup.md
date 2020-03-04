---
title: "Setup"
metaTitle: "Overview and Goals"
metaDescription: "Overview and Goals"
---

# Install Containers

Make sure you have containers up and running on your laptop.
I use podman for rootless containers. You can use docker which
may be easier to install and use. For our purposes today, 
they are equivalent.

* macos: https://docs.docker.com/docker-for-mac
* windows: You need to install wsl2 to get a linux environment
  on top of windows. wsl2 is much more efficient than wsl1.
  * wsl2: https://docs.microsoft.com/en-us/windows/wsl/wsl2-install
  * docker: https://docs.docker.com/docker-for-windows/install/
    * Choose linux containers, not windows containers. 
  * You can also get podman on wsl2: https://www.redhat.com/sysadmin/podman-windows-wsl2
* linux: 
  * docker: https://docs.docker.com/install 
  * podman: Your distribution probably has a package available, e.g., `dnf install podman`

If you are interested in running all of this on the cloud you can
use a fedore "core" image on aws. Everything that works in this
workshop will work on a cloud vm. See https://getfedora.org/coreos/download/ 
for coreos images on AWS. Choose a t2.xxlarge for example to run
the examples in this class.

There are other tools that come with containers, such as `docker-compose` that allows you to coordinate multiple containers at once. However, CLI tools for local development like [minikube](https://kubernetes.io/docs/tasks/tools/install-minikube) , are desgined to give you a kubernetes-like API for local development so you can skip `docker-compose`. The idea is to access a kubernetes cluster like a local development cluster. That dev cluster could be a single vm running on your desktop with several required kubernetes services, or tool to push containers to kubernetes without requiring a CI/CD pipeline to run. 

For example, [draft](https://draft.sh/) helps auto deploy containers to a cluster (runnig somewhere). But to use draft, you need to also use [helm](https://helm.sh/) and more. There's alot of choices and most of it is complicated.

All of the container technologies are moving fast so there could be lots of
extra tools available you may want to use. 

As always, avoid containers if you can. Why incur the extra cognitive overhead
unless you need to!

# Workshop Git

Grab the git repo at https://www.github.com/aappddeevv/scalar2020.

```sh
git clone https://www.github.com/aappddeevv/scalar2020.git

cd scalar2020

export TOP=`pwd`

mkdir work

export WORK=$TOP/work
```

Each module has its own directory. All of the examples discussed in the
text have full code examples in their respective directory.

Throughout we will indicate that you should go to the top of the git tree by indicating `cd $TOP` or `cd $WORK`. When we are stepping through code as if you were creating the code from scratch, you can create a directory in `$WORK`. All of the finished code is located in `$TOP/modules` so you can always easily look at the answer.



# Get Ahead of the Curve

Containers can take a long time to dowload on a slow connection. If you want to start some downloads now, run these image pulls. We will need them later:

```sh
docker pull node:alpine
docker pull tensorflow/tensorflow:latest-py3-jupyter
docker pull tensorflow/tensorflow:1.12.3-py3
```

