const config = {
  gatsby: {
    pathPrefix: "/scalar2020",
    siteUrl: "https://aappddeevv.github.io",
    gaTrackingId: null,
	trailingSlash: true,
  },
  header: {
    logo:
      "http://scalar-conf.com/images/scalar-logo.svg",
    logoLink: "http://www.github.com/aappddeevv/scalar2020",
    title: "Scalar 2020 Fullstack Workshop",
    githubUrl: "https://github.com/aappddeevv/scalar2020",
    helpUrl: "",
    tweetText: "",
    links: [{ text: "", link: "" }],
    search: {
      enabled: false,
      indexName: "",
      algoliaAppId: process.env.GATSBY_ALGOLIA_APP_ID,
      algoliaSearchKey: process.env.GATSBY_ALGOLIA_SEARCH_KEY,
      algoliaAdminKey: process.env.ALGOLIA_ADMIN_KEY
    }
  },
  sidebar: {
    forcedNavOrder:[
	"/introduction/",
	"/module1/",
	"/module2/",
	"/codeblock/"
],
	collapsedNav: ["/module1/","/module2/","/codeblock/","/introduction/"],
    links: [{ text: "Me", link: "https://www.github.com/aappddeevv/scalar2020" }],
    frontline: false,
    ignoreIndex: true
  },
  siteMetadata: {
    title: "Scalar 2020 Fullstack Workshop",
    description: "Fullstack workshop tutorial.",
    ogImage: null,
    docsLocation:
      "https://github.com/hasura/gatsby-gitbook-boilerplate/tree/master/content",
    favicon: "https://graphql-engine-cdn.hasura.io/img/hasura_icon_black.svg"
  },
  pwa: {
    enabled: false, // disabling this will also remove the existing service worker.
    manifest: {
      name: "Scalar 2020 Fullstack Workshop",
      short_name: "Fullstack Workshop",
      start_url: "/",
      background_color: "#6b37bf",
      theme_color: "#6b37bf",
      display: "standalone",
      crossOrigin: "use-credentials",
      icons: [
        {
          src: "src/pwa-512.png",
          sizes: `512x512`,
          type: `image/png`
        }
      ]
    }
  }
};

module.exports = config;
