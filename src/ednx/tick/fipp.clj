(ns ednx.tick.fipp
  (:require
   [time-literals.data-readers] ;; For literals
   [time-literals.read-write] ;; For printing/writing
   [fipp.ednize]
   [ednx.handler :refer [add-fipp-printers!]])
  (:import
   [java.time Period
    LocalDate
    LocalDateTime
    ZonedDateTime
    OffsetTime
    Instant
    OffsetDateTime
    ZoneId
    DayOfWeek
    LocalTime
    Month
    Duration
    Year
    YearMonth
    MonthDay]))


(defn add-tick-fipp-printers! []
  (add-fipp-printers!
   {LocalDate (tagged-literal 'time/date (str x))
    LocalDateTime (tagged-literal 'time/date-time (str x))
    ZonedDateTime (tagged-literal 'time/zoned-date-time (str x))
    Instant (tagged-literal 'time/instant (str x))}))


(comment
  (add-tick-fipp-printers!)
  (require '[tick.core :as t])
  (require '[ednx.fipp :refer [pprint]])
  (require '[ednx.edn :refer [read-edn]])
  (-> (t/instant)
      (pprint)
      (read-edn)))