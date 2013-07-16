(ns stammtisch.meikel-version
  (:use [iota :rename
         {vec iota-vec,
          subvec iota-subvec}]
        [clojure.core.reducers :as r
         ]  )
  )


;(-new-main "/home/catonano/Berlino/stammtisch/moby-dic-originale.txt" "meikel")
(def lines-in-the-file (iota/vec "/home/catonano/Berlino/stammtisch/moby-dic-originale.txt"))

;(meikel-frequencies (words lines-in-the-file))


(defn words-per-line [line]
  (re-seq #"[a-z]+" line)
)

(defn words [coll-of-lines]
  (mapcat words-per-line
          (filter identity coll-of-lines)) )

(defn meikel-frequencies
  [coll]
  (fold
    (fn
      ([] {})
      ([a b] (merge-with + a b)))
      (fn [words-occurrences-map word] (update-in words-occurrences-map [word] (fnil inc 0)))
      coll
  )
)


