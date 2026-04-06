(ns ednx.demo-data
  (:require
   [tick.core :as t]))

(def demo-data {:a 34
                :date {:date-local (t/date-time)
                       :date (t/date)
                       :date-instant (t/instant)
                       :date-zoned (t/zoned-date-time)}})