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
