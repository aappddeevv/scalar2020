# NLP

NLP is a fast moving field. New results are announced monthly. 2017-2018 was a year with many new discoveries that reshaped the field. While NLP goes back to the 1950s there are a few specific areas of NLP that are the focus today:

* Entailment: A statement in a document is true and therefore this statement is true.
* ...



# AI / Machine Learning Libraries

There is a wild growth of machine learning libraries in the world although over time, there is an expansion of new libraries, a consolidation then another expansion as new discoversie are integrated into mainstream libraries.



## Core ML Libs

The world is consolidating somewhat on PT and TF. TF seeks to provide a full stack of train, assess, deploy, monitor tools across a very large surface of high-performance and mobile devices--very ambitious effort.

- Pytorch (PT) 
- Tensorflow (TF) 
- SparkNLP
- From snow labs, algorithms rewritten to run on spark 
- Gensim (nlp focused) 
- Deeplearning4j (jvm api but c/c++ engine)
- Sklearn (scikit learn)
- Apache OpenNLP (java)
- StandfordNLP (java, some in python)
- SpaCy (nlp focused)
- Flux (julia)
- ...on and on and in other languages such as R...



Even the core libs are undergoing tremendous change. Tensorflow:

* Rewriting in swift so that one language is used for all layers.
* Keep it easy to use in Python and other languages that other people use.
* Move from a very functional API exposed to users to a more "eager" interface which is easier for ML people.
  * Current model creates a description of a computation in graph form then submits it for evaluation.
* Creating an Machine Learning Intermediate Representation (MLIR) that sits above the LLVM layer--a common model for languages to move to probably similar in spirit to the idea behind scala's Tasty. This would make provding APIs in any language much easier.

Most libraries are adding layers of abstraction in order to be able to retarget to different compute environments as the algorithms and deployment models change, e.g., run machine learning on your phone as much as possible.



## Layers to make it easier to use the core: 

- Keras: hides neural network details,
- https://github.com/ThilinaRajapakse/simpletransformers
  - One linears in python
- Huggingface (pytorch)
  - Make PT and TF easier to use
- Fast.ai (on top pytorch and now tf, adds to algorithms)
- …  

## Runtime environments:

- Containers
- Kubernetes e.g. Kubernetes and Kubeflow
- GPUs/TPUs
- CPUS



## Runtime Branded Environments: 

- Kubeflow: Run ML on orchestrated containers 
- AWS Sagemaker
  - Fargate just runs containers as if you were using the local CLI
- Azure
- Google Cloud Platform
- Colab - GCP hosted notebooks and ML environment



## Distributed Communication

Alot of distributed communication is needed so you find different lib used:

- Zeromq 
  - Java clients seem challenging to get going with but there are 3 java client choices
  - Been around a long time and still an active community 
  - Easy to use in python, js, c 
- Nats.io 
  - Seems to need a central server to supports its patterns 
- Rsocket 
  - Strong java, js support 
  - Follows reactive manifesto
- GRPC 
  - Works over http2 so it also has some limits there 
- Socket.io 
  - Not sure its still a focus 
- Akka 
  - Higher level than just per socket based libs like above. 
  - Has the async push/pull pattern like rsocket 
  - Java centric 