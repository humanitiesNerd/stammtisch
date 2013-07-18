(ns stammtisch.meikel-version
  (:use [iota :rename
         {vec iota-vec,
          subvec iota-subvec}]
        [clojure.core.reducers :as r
         ]  
       [stammtisch.common-functions]
  )
)


;(meikel-frequencies (words lines-in-the-file))




(defn meikel-words [coll-of-lines]
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

(defn meikel-process-my-file [path-to-the-file]
 (let [lines-in-the-file (iota/vec path-to-the-file)]
   (sort-by second >(meikel-frequencies (meikel-words lines-in-the-file)))
 )
)
