---
title: "NLP"
---

Natural Language Processing (NLP) is a fast moving subfeld in the general AI / Machine Learning field. 

New results are routinely announced quarterly. 2017-2018 announced new approaches that reshaped the field.



# AI / Machine Learning Libraries

There is wild growth of machine learning libraries today.

The general pattern across 2-3 cycles of the AI field is that:

1. New approaches shift the paradigm.
2. New libraries appear everywhere all with subtle and large differences
3. Libraries consolidate into a few main choices.
4. Nuanced approaches develop, spawning new libraries or add-ons/plugins.
5. Major discovery occurs.
6. Cycle repeats.

Very Thomas Kuhn'ish: https://plato.stanford.edu/entries/thomas-kuhn/

We are between 2 and 3 after the last large NLP shift in 2017-2018 with the introduction of new neural network architectures and word representations (which happened around 2014).



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

A generation ago, almost all ML libs were written in c or c++...and they still are. However, parts of the libraries and some of the higher level APIs were then exposed in languages like python. That's been the dominant model for awhile now. But that is starting to change.

Tensorflow, for example, is undergoing tremendous change right now even though TF2 was released recently:

* Rewriting in swift so that one language is used for all layers.
* Keep it easy to use in Python and other languages that other people use.
* Move from a very functional API exposed to users to a more "eager" interface which is easier for ML people.
  * Current model creates a description of a computation in graph form then submits it for evaluation.
* Creating an Machine Learning Intermediate Representation (MLIR) that sits above the LLVM layer--a common model for languages to move to probably similar in spirit to the idea behind scala's Tasty. This would make provding APIs in any language much easier.

Most libraries are adding layers of abstraction in order to be able to retarget to different compute environments as the algorithms and deployment models change, e.g., run machine learning on your phone as much as possible.



## Layers to make it easier to use the core: 

- Keras: hides neural network details
- Huggingface (pytorch) tranformers
  - Make PT (mostly) and TF easier to use
- https://github.com/ThilinaRajapakse/simpletransformers
- Fast.ai (on top pytorch and now tf, adds to algorithms)
- …  

## Runtime environments:

- Containers
- Kubernetes e.g. Kubernetes with orchestration layers like Kubeflow
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
