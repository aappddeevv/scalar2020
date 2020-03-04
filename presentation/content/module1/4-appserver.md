---
title: "Simple App Server"
metaTitle: "Simple Application Server"
metaDescription: "Simple Application Server"
---

Let's create a simple app server using nodejs.

We already have a container that we can run that has node.js in it. So let's create a simple server similar to what we might find in the wild. We'll have it use typescript for the javascript parts. Later, in another module, we will add the scala portions.

The test server is located at `$TOP/module/module1/40_testserver`.

We will build up the testserver, showing the steps below. If you jump to the bottom of this page, you can see how to run it without waiting.

To avoid, hassle, see if any containers are currently running. The workshop assumes that you will start a container for building the app server.

```sh
docker ps

# observer containers running, capture any container ids that should be stopped

docker stop <container id>
```



# Create a Working Environment

We need a few files, not much, to setup a test server:

* package.json: For holding our package dependencies and script definitions.
  * The list of dependencies are installed using `npm`.
* tsconfig.json: For running typescript
* src/server.ts: The server

We need to start our container and mount a directory for the project.

```sh
cd $WORK

mkdir appserver

docker run -v ./appserver:/appserver -w /appserver -p 3000:3000 --rm -it dev:latest

// or for podman

podman run -v ./appserver:/appserver:z -w /appserver -p 3000:3000 --rm -it dev:latest
```

You will want to list the constainer id using `docker ps`. If you need another shell in the container run:

```sh
docker exec -it <container id> sh
```

All editing can be performed in the appserver directory from your host. The host `$WORK/appserver` direcotry is mapped to the `/appserver` container directory.

## Create package.json, install dependencies

Install our dependencies and create config files:

```sh
npm init -y

npm install -D typescript@rc @types/node @types/express ts-node npm-run-all dotenv

npm install nodeman apollo-server graphql

npx tsc --init --rootDir src --outDir dist

mkdir src

mkdir src/public

touch src/server.ts
```



We are using the [apollo graphql server](https://www.apollographql.com/docs/apollo-server/getting-started/). The server driver:

```typescript
// src/server.ts
import { ApolloServer, gql } from "apollo-server-express";
import * as path from "path";
import fs from "fs";
import express from "express";

const file = "schema.graphql";
const typeDefs = gql(fs.readFileSync(path.join(__dirname, file), "utf8"));

// fake database
const documents = [{ id: "1", subject: "reg1", content: "regulatory content" }];

const resolvers = {
  Query: {
    documents: () => documents
  }
};

const PORT = process.env.PORT ?? 3000;
const HOST = process.env.HOST ?? "0.0.0.0";

const app = express();
app.use("/public", express.static(path.join(__dirname, "public")));
app.use("/healthcheck", (req, resp) => {
  resp.json({ status: "ok" });
});

const server = new ApolloServer({ typeDefs, resolvers });
server.applyMiddleware({
  app,
  path: "/graphql"
});

app.listen(+PORT, HOST, () => {
  console.log(`scalar2020 serving at http://${HOST}:${PORT}`);
});
```



We have kept the schema file separate so we can access it later with other tools that help us on the scala side. If its separate, node must read it at program start which is why we had the `fs.readSync` above.

```json
type Query {
  documents: [Document!]!
}

type Document {
  id: ID!
  subject: String
  content: String
}
```

And we need a few scripts:

```json
// package.json
 "scripts": {
+    "compile-watch:dev": "tsc -w",
+    "monitor:dev": "nodemon -V -w package.json -w .env -w dist -w src/schema.graphql -w src/public --inspect --exec npm run dev",
+    "serve:dev": "node dist/server.js",
+    "compile-watch-monitor:dev": "run-p compile-watch:dev monitor:dev",
+    "copy-assets": "mkdir -p dist && cp -r ./src/public ./dist && cp ./src/schema.graphql ./dist",
+    "dev": "run-p copy-assets serve:dev",
+    "start": "run-s compile-watch-monitor:dev"
   }
```



# Run and Test

We could make the scripts in package.json much more cross-platform, but for our purposes, we will use unix commands directly in the scripts.

To run:

```sha
npm start
```

Normally, `npm start` starts the production run, but in our case it starts in dev mode which is sufficient for the workshop. The script `"start": "run-s dev"` is simply an alias for `npm run dev`.

Browse to `https://localhost:3000/graphql` to see the graphql query playground.

```sh
curl http://localhost:3000/healthcheck
//{"status":"ok"}
```

With a working app server we can focus on the machine learning and the scala portions of the workshop. We won't work in typescript for the rest of the server or the browser client.