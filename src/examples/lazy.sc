package examples

//打印repos
object lazyit {
    class readRepos(val url: String) {
        lazy val htmls = {
            println("fetching from url...")
            scala.io.Source.fromURL(url).getLines().toList
        }
        var from = 0
        lazy val repos = {
            for (line <- htmls if line.contains("repo-list-name")) yield {
                var index = htmls.indexOf(line, from)
                var repo = htmls(index + 2)
                from = index + 2
                repo.substring(0, repo.indexOf("</a>"))
            }
        }
    }

    val version = new readRepos(
        "https://github.com/uohzoaix?tab=repositories")
                                                  //> version  : examples.lazyit.readRepos = examples.lazyit$readRepos@3b362ca8
    println("get repos from " + version.url)      //> get repos from https://github.com/uohzoaix?tab=repositories
    version.repos.foreach(println _)              //> fetching from url...
                                                  //|       scalas
                                                  //|       word2vec4j
                                                  //|       Word2Vec-on-Spark
                                                  //|       gor
                                                  //|       selenium-study
                                                  //|       jnlp
                                                  //|       goleveldb
                                                  //|       gonlp
                                                  //|       glove_py_model_load
                                                  //|       pysonar2
                                                  //|       golib
                                                  //|       gopkg
                                                  //|       pengyifan-commons
                                                  //|       CoreNLP
                                                  //|       CiteSeerX
                                                  //|       ardublock
                                                  //|       nlp-lang
                                                  //|       beansdb
                                                  //|       PredictionIO-Java-SDK
                                                  //|       java-redis
                                                  //|       sinaweibopy
                                                  //|       incubator-storm
                                                  //|       lucene-solr
                                                  //|       MSGQuake
                                                  //|       quartz
                                                  //|       stormManager
                                                  //|       studies
                                                  //|       openNLP
                                                  //|       stan-cn-nlp
                                                  //|       solr-recommender
                                                  //|       Rlan
                                                  //|       RealTimeTraffic
                                                  //|       hadoop4cba
                                                  //|       maven_mahout_template
                                                  //|       trident-ml
                                                  //|       curator
                                                  //|       word2vec
                                                  //|       solrPlugin
                                                  //|       NLPWithMahout
                                                  //|       gosegment
                                                  //|       twister
                                                  //|       bacon
                                                  //|       exercises-golang
                                                  //|       kafka
                                                  //|       go-nlp
                                                  //|       lucene-didyoumean
}