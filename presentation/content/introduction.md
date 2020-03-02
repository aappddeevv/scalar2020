---
title: "Introduction"
metaTitle: "Overview and Goals"
metaDescription: "Overview and Goals"
---

# What is full stack?

All web projects are really fullstack in the the sense there is a frontend and backend.

In this workshop, fulstack means that a single developer will develop all of the parts of the solution.
While this is not common for large web applications in the corporate world, full stack developers
are found in both small and large companies. For example, there may be a need to develop a small
auxiallary application that performs a highly specialized function e.g. marketing list management
or a corporate dashboard indepndent of any other BI-dashboarfd application.

# Summary of Technologies

- scala.js: A version of scala that outputs javascript instead of JVM bytecode.
- zio: A scala effects framework. A bifunctor design and runtime management capabilities make this a popular choice. When running on the javascript platform, zio helps us manage asynchronous composition.
- containers: A lightweight virtual environment that allows you to control dependencies and runtime behavior.
- nodejs: A javascript based environment for running backend services, such as express, a nodejs http server framework.
- browser: Front-end UI. Chrome,firefox or safari are popular choices.
- python: Popular programming language for machine learning processing.
- BERT et al: NLP machine learning model that uses "transfer learning" to build a better NLP product.
- TensorFlow et al: A popular machine learing library from google. We are not using pytorch, another popular machine learning library from facebook.\
- scalajs-reaction: React hooks facade for the UI.

# Key Pre-requisites

There are a few pre-requisites that you need to run this workshop.

- Ability to run a few containers.
  - You'll need a beefy laptop capable of running containers. You can run containers under windows, macos or linux on your laptop. You can also run containers in the cloud. How you run the containers during the workshop is up to you, but it will be assumed in the configuration and scripts that the containers are running locally.
  - You may choose not to run containers at all but you should only do this if you can completely control your laptop's configuration.

The ability to run a few containers generally means that you need a laptop with 16GB of memory and 30GB of free disk space.
