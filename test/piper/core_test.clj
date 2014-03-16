(ns piper.core-test
  (:require [clojure.test :refer :all]
            [piper.core :as pi]))

; Just an example
(pi/pipe {:a 3 :b 11}
         (pi/given #(> (:a %) 2)
                   #(update-in % [:a] inc))
         (pi/tap "i")
         (pi/unless-nil #(assoc % :c 25))
         (pi/tap "ii")
         #(get % :a)
         (pi/tap "iii")
         (pi/times 6 inc)
         (pi/tap "iv"))
