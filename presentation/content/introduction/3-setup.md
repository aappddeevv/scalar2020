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

All of the container technologies are moving fast so there could be lots of
extra tools available you may want to use. 

As always, avoid containers if you can. Why incur the extra cognative overhead
unless you need to!

# Workshop Git

Grab the git repo at https://www.github.com/aappddeevv/scalar2020.

```sh
cd <your work dir>

git clone https://www.github.com/aappddeevv/scalar2020.git

cd scalar2020
```

Each module has its own directory. All of the examples discussed in the
text have full code examples in their respective directory.

