(ns piper.core)

(defn pipe [value & fns]
  (reduce (fn [acc cur] (cur acc)) value fns))

(defn given [pred f]
  (fn [x]
    (if (pred x)
      (f x)
      x)))

(defn unless-nil [f]
  (fn [x]
    (if (nil? x)
      nil
      (f x))))

(defn tap [msg]
  (fn [x]
    (do
      (println (str msg ": " x))
      x)))

(defn times [n f]
  (fn [x]
    (loop [value x
           remaining-turns n]
      (if (zero? remaining-turns)
        value
        (recur (f value)
               (dec remaining-turns))))))
