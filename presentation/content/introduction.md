---
title: "Introduction"
metaTitle: "Overview and Goals"
metaDescription: "Overview and Goals"
---

# What is full stack?

All web projects are really fullstack in the the sense there is a frontend, backend and some domain specific content.

In this workshop, fullstack means that a single developer will develop all of the parts of the solution.
While this is not common for large web applications in the corporate world, full stack developers
are found in both small and large companies. For example, there may be a need to develop a small
auxiallary application that performs a highly specialized function e.g. marketing list management
or a corporate dashboard indepndent of any other BI-dashboarfd application.

Some people believe that full stack developers no longer exist as each area has become too complicated and there is alot of truth to that. Think Feynman and physics...he was one of the last people it is said, to master all areas of physics before it grew too large.

Today, a full stack developer may "major" in one area but play roles in their "minor" areas. Each area would still have a team and the full stack developer could provide glue between the parts or fill in where needed based on the development lifecycle.



# Summary of Technologies

- scala.js: A version of scala that outputs javascript instead of JVM bytecode.
- zio: A scala effects framework. A bifunctor design and runtime management capabilities make this a popular choice. When running on the javascript platform, zio helps us manage asynchronous composition.
- containers: A lightweight virtual environment that allows you to control dependencies and runtime behavior.
- nodejs: A javascript based environment for running backend services, such as express, a nodejs http server framework.
- browser: Front-end UI. Chrome, firefox or safari are popular choices.
- python: Popular programming language for machine learning processing.
- BERT et al: NLP machine learning model that uses "transfer learning" to build a better NLP product.
- TensorFlow et al: A popular machine learing library from google. We are not using pytorch, another popular machine learning library from facebook.
- scalajs-reaction: React hooks facade for the UI.



# Pre-requisites

There are a few pre-requisites that you need to run this workshop.

- Ability to run a few containers.
  - You'll need a beefy laptop capable of running containers. You can run containers under windows, macos or linux on your laptop. You can also run containers in the cloud. How you run the containers during the workshop is up to you, but it will be assumed in the configuration and scripts that the containers are running locally.
  - You may choose not to run containers at all but you should only do this if you can completely control your laptop's configuration.

The ability to run a few containers generally means that you need a laptop with 16GB of memory and 30GB of free disk space.

You can optionally run this all in the cloud even for this cloud. A AWS script is included to spin up an container focused ec2 instance. It would cost around $1.50 USD to use during this class but its up to you.





# Workshop Problem Definition

Our workshop goal is to build a full stack that creates a customized search/analysis environment based on regulatory data from 2 countries.

Customizing the search/analysis space is the general idea behind of alot of interesting work these days and a significant part of the NLP space is about this concept.

There is alot of talk about how AI is changing everything and that is true in certain areas. But there is still a long way to go. Dramatic improvements in "learning" are needed to make it more useful in areas where society can  benefit, e.g., healthcare, security and defense.