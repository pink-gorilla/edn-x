(ns ednx.handler)


(defonce data-readers-a (atom {}))

(defn add-edn-handlers!
  ([data-readers-map]
   (swap! data-readers-a merge data-readers-map)))


(defmacro add-fipp-printers!
  [fipp-printers-map]
  `(extend-protocol fipp.ednize/IEdn
     ~@(mapcat (fn [[typ body]]
                 `[~typ (fipp.ednize/-edn [~'x] ~body)])
               fipp-printers-map)))