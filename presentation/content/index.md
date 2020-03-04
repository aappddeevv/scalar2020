---
title: "Scala 2020 Workshop"
metaTitle: "Scala 2020 Workshop"
metaDescription: "Scala 2020 Workshop"
---

# Hands-On Lab Objective

FULL STACK DEVELOPMENT LEVERAGING SCALA.JS, ML/NLP AND A MIXED CODEBASE

Develop a full stack application in a mixed language environment using scala.js, node.js express, react, zio, and python. The full stack approach will use a scala.js-first model and incorporates a python-based NLP based model developed (based on BERT's pre-trained model) in class to power an "intelligent" API .

The development environment will use a container-based model for both the backend and frontend. Containers allows precise control over the dependencies. The backend will contain a combined node.js express web server based on scala.js and zio. A separate python based API server will provide the NLP model. The frontend container will be a node.js express web server serving static assets including the frontend scala.js web application. The frontend will be based on react using the scalajs-reaction facade bundled with webpack and incorporate zio. The NLP model will be built using tensorflow/keras and use a standard dataset available in tensorflow. The use of zio will be around effects management and concurrency versus parallelism.

The allotted time is rough ~5 hours with a lunch and personal breaks.
