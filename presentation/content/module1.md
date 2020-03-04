---
title: "Module 1"
metaTitle: "Module 1"
metaDescription: "Module 1"
---

Module 1 covers the basics and helps ensure we are ready for
more complicated development tasks.

* Virtual Environments
* Setup a dev container
* Fetch data
* Create a basic app server using node.js.



We are assuming node.js as the app server because a full stack approach sometimes must integrated into a mixed code base. You have several transpiled languages to choose from that target the js environment, including typescript, however, if you have scala skills its fairly easy to leverage those. While graal may allow you to use a more jvm/js mixed base with more performance, more real word experience with graal in a mixed environment appears to be needed.

There are many scala/jvm based HTTP servers available, however, we also need to consider our dependencies. Today, for example, if you want to serve up graphql, you may need to leverage some of the large investments in graphql server technology that is flowing into the js runtime world. Also, leveraging the js runtime may make data munging easier if your data is in json already. Sometimes dependencies win. 

If your server is more of a gateway server that brokers other back-end services and those services speak some other protocol, then there may be more impetus to use a non-js based app server.

There are alot of factor, so...it depends.

