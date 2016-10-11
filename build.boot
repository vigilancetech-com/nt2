(set-env!
  :dependencies '[

                  [cider/cider-nrepl "0.14.0-SNAPSHOT" :scope "test"]
                  [refactor-nrepl "2.3.0-SNAPSHOT" :scope "test"]
                  [org.clojure/tools.nrepl "0.2.12" :scope "test"]
                  [adzerk/boot-cljs-repl "0.3.0" :scope "test"]
                  [com.cemerick/piggieback "0.2.2-SNAPSHOT" :scope "test"]
                  [weasel "0.7.0" :scope "test"]
                  [adzerk/boot-cljs          "1.7.228-1"]
                  [hoplon/ui "0.0.1-SNAPSHOT"]
                  [adzerk/boot-reload        "0.4.12"]
                  [hoplon/boot-hoplon        "0.3.0-SNAPSHOT"]
                  [hoplon/hoplon             "6.0.0-alpha16"]
                  [org.clojure/clojure       "1.8.0"]
                  [org.clojure/clojurescript "1.9.229"]
                  [tailrecursion/boot-jetty  "0.1.3"]]
  :source-paths #{"src"}
  :asset-paths  #{"assets"})

(require
  '[adzerk.boot-cljs         :refer [cljs]]
  '[adzerk.boot-cljs-repl    :refer [cljs-repl start-repl]]

  '[adzerk.boot-reload       :refer [reload]]
  '[hoplon.boot-hoplon       :refer [hoplon prerender ns+]]
  '[tailrecursion.boot-jetty :refer [serve]])

(deftask dev
  "Build nt2 for local development."
  []
  (comp
    (watch)
    (speak)
    (hoplon)
    (reload)
    (cljs-repl)
    (cljs)
    (serve :port 8000)))

(deftask prod
  "Build nt2 for production deployment."
  []
  (comp
    (hoplon)
    (cljs :optimizations :advanced)
    (target :dir #{"target"})))
