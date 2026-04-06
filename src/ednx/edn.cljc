(ns ednx.edn
  (:require
   [ednx.handler :refer [data-readers-a]]
   #?(:clj [clojure.edn :as edn]
      :cljs [cljs.reader :as edn])))

(defn default-reader
  "A default reader, for when we don't know what's coming in."
  [t v]
  {:tag t :value v})

#?(:clj
   (defn read-edn [s]
     (edn/read-string {:default default-reader
                       :readers @data-readers-a} s)))

#?(:cljs
   (defn read-edn [s]
     (edn/read-string {:default default-reader
                       :readers @data-readers-a} s)))


#?(:clj
   (defn spit-edn [filename data]
     (spit filename (pr-str data))))

#?(:clj
   (defn slurp-edn [filename]
     (-> filename slurp read-edn)))


; [clojure.tagged-literals]
; (set! *default-data-reader-fn* tagged-literal)