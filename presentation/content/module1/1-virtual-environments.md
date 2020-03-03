# Virtual Environments

There are many ways to create a virtual environment for development or production.
They all have trade-offs and some play a dual role of creating a "shell" environment
as needed and a package/app manager:

* Heavyweight:
  * virtual machine like vmware, virt or virtualbox
  * podman/docker: containers, lightweight virtualization
  * wsl2: window subsystem for linux, use 2, its better
* Lightweight:
  * python: virtualenv, pipenv
  * java: sdk
  * node: nvm
  * individual build tools

There are also many external package/app managers that sit on top of
the OS package manager to help you when your OS package manager is not enough:

* coursier (scala friendly)
* brew (linux, macos, windows)
* nuget (linux, macos, windows)

Some of these are large installs that require root access, some not.
It is quite common now to install by calling a curl fetch command
and piping the "install file" through `sh` for a user-specific install.

Generally, you can use some combination of all of these to create
a development environment for any specific module of your application. 
Using scripting, we could create 
"recipes" using any of these tools quite easily.

> You really do not heavyweight virtualization if you only need a single environment at any given time, your build tool can reproduce the builds and that's all you need, the dependencies are flexible or your project demands are not overly complex.

Some of the "virtualization" techniques above have significant side-effects
on your system or at least on your user environment. For example, installing
brew on linux is best done by creating a `homebrew` user. Others are difficult
to install for just a single user requiring an OS package install instead.

If we want to create an environment that more closely matches
production (but dev is always different in most cases) or that requires
multiple "environments" each with complex dependencies, containers
provides a popular way that balances many factors and has an obvious
and single point of impact on your user environment.

