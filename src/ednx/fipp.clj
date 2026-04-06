(ns ednx.fipp
  (:require
   [fipp.clojure]
   [tick.core :as t])
  (:import
   [java.io StringWriter]))

(defn pprint
  "Pretty print data using fipp.clojure/pprint
   opts is a map of options to pass to fipp.clojure/pprint
   it is uses additional fipp-edn-writers/readers managed in ednx.handler"
  ([data opts]
   (let [sw (StringWriter.)]
     (fipp.clojure/pprint data (merge {:width 60 :print-meta false}
                                      opts
                                      {:writer sw}))
     (str sw)))
  ([data]
   (pprint data {})))

(defn spit-fipp
  ([filename data opts]
   (spit filename (pprint data opts)))
  ([filename data]
   (spit filename (pprint data))))


(defn spit-fipp-comment
  ([file-name data comment]
   (spit-fipp-comment file-name data comment {}))
  ([file-name data comment opts]
   (let [comment (str ";; " comment "\r\n;; saved on " (t/instant) "\r\n")
         edn-s (pprint data opts)
         s (str comment edn-s)]
     (spit file-name s)
     data)))


(comment
  (spit-fipp ".clj-kondo/bongo.edn" {:a 1 :b 2})
  (spit-fipp-comment ".clj-kondo/bongo.edn" {:a 1 :b 2} "demo")
  (spit-fipp-comment ".clj-kondo/bongo.edn" {:a 1 :b 2} "demo-big" {:width 100})

  (require '[ednx.edn :refer [slurp-edn]])
  (slurp-edn ".clj-kondo/bongo.edn")

 ; 
  )