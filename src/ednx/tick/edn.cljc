(ns ednx.tick.edn
  (:require
   #?(:clj  [time-literals.data-readers] ;; For literals
      :cljs [time-literals.data-readers-cljs])
   [time-literals.read-write] ;; For printing/writing
   [ednx.handler :refer [add-edn-handlers!]]))

; com.widdindustries/time-literals
; https://github.com/henryw374/time-literals/tree/master/src/time_literals


(defn add-tick-edn-handlers! []
  (add-edn-handlers! time-literals.read-write/tags))


(comment

  (add-tick-edn-handlers!)
  (require '[ednx.edn :refer [read-edn]])
    
  (read-edn "#inst \"1985-04-12T23:20:50.52Z\"")
  (read-edn  "#time/date \"2021-11-04\"")
  (read-edn "#time/date-time \"2021-11-04T00:52:59.694154533\"")
  
  (require '[tick.core :as t])
  (-> (t/instant)
      (pr-str)
      (read-edn))
  
 (-> (t/date-time)
    (pr-str)
    (read-edn))

  (-> (t/date)
      (pr-str)
      (read-edn))

;
  )
