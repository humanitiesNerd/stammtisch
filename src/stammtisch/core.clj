(ns stammtisch.core
  (:gen-class)
  (:use [stammtisch.meikel-version][stammtisch.common-functions])
  (:require clojure.java.io
  [criterium.core :as cr])
  )



;(my-main "/home/catonano/Berlino/stammtisch/moby-dic-originale.txt")
;(-main "/home/catonano/Berlino/stammtisch/moby-dic-originale.txt")
; (-new-main "/home/catonano/Berlino/stammtisch/moby-dic-originale.txt" "vanilla")
;(-new-main "/home/catonano/Berlino/stammtisch/moby-dic-originale.txt" "reducers")
;(-new-main "/home/catonano/Berlino/stammtisch/moby-dic-originale.txt" "meikel")

(defn lines-in-the-file [file-reader]
   (line-seq file-reader)
)


(defn vanilla-words [coll-of-lines]
  (mapcat words-per-line coll-of-lines)
)


(defn vanilla-process-my-file [file-reader]
 (sort-by second > (frequencies (vanilla-words (lines-in-the-file file-reader)))
 )
)




(defn vanilla [path-to-the-file]
   (with-open [mio-file (clojure.java.io/reader path-to-the-file)]
      (vanilla-process-my-file mio-file)
   )
)

(defn meikel [path-to-the-file]
      ;(process-my-file-improved mio-file)
      (meikel-process-my-file path-to-the-file)
)

(defn reducers [path-to-the-file]
   (with-open [mio-file (clojure.java.io/reader path-to-the-file)]
      (vanilla-process-my-file mio-file)
   )
)


(defn print-it-vanilla [path-to-file]
  (let [to-be-printed (take 10 (vanilla path-to-file))]
    (dorun (map #(println %) to-be-printed ))  )
  )

(defn print-it-meikel [path-to-file]
  (let [to-be-printed (take 10 (meikel path-to-file))]
    (dorun (map #(println %) to-be-printed ))  )
  )

(defn print-it-reducers [path-to-file]
  (let [to-be-printed (take 10 (meikel path-to-file))]
    (dorun (map #(println %) to-be-printed ))  )
  )


(defn main-vanilla* [path-to-file]
(print-it-vanilla path-to-file)
)

(defn main-meikel* [path-to-file]
(print-it-meikel path-to-file)
)

(defn main-reducers* [path-to-file]
(print-it-reducers path-to-file)
)




(defn -new-main [path-to-file version & args]
   (let [benchmarking (first args)]
   (case version
      ("vanilla")
         (if (= benchmarking "-b")
            (cr/quick-bench (main-vanilla* path-to-file) :verbose)
            (main-vanilla* path-to-file)
         )
      ("reducers")
         (if (= benchmarking "-b")
            (cr/quick-bench (main-reducers* path-to-file) :verbose)
            (main-reducers* path-to-file)
         )

      ("meikel")
         (if (= benchmarking "-b")
            (cr/quick-bench (main-meikel* path-to-file) :verbose)
            (main-meikel* path-to-file)
         )

   )
   )
)
