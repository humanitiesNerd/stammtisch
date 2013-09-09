(ns stammtisch.common-functions)

(defn words-per-line [line]
(re-seq #"\w+" line)
)
