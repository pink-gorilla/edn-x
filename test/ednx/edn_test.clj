(ns ednx.edn-test
  (:require
   [clojure.test :refer [deftest is]]
   [ednx.edn :refer [spit-edn slurp-edn read-edn]]
   [ednx.fipp :refer [pprint]]
   [ednx.demo-data :refer [demo-data]]
   [ednx.tick.edn :refer [add-tick-edn-handlers!]]
   [ednx.tick.fipp :refer [add-tick-fipp-printers!]]
   ))

(def filename "/tmp/edn-test.edn")

(add-tick-edn-handlers!)
(add-tick-fipp-printers!)

(deftest encoding-tick-edn-test []
  (spit-edn filename demo-data)
  (is (= demo-data (slurp-edn filename))))

(deftest encoding-tick-fipp-test
  (let [sdate (str "#time/date \"2011-01-01\"" "\n")
        stime (str "#time/date-time \"2021-11-04T00:52:59.694154533\"" "\n")]
    (is (= sdate (-> sdate read-edn pprint)))
    (is (= stime (-> stime read-edn pprint)))))
