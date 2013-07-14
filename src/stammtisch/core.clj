(ns stammtisch.core
  (:gen-class)
  (:require clojure.java.io
  [criterium.core :as cr])
  )

(defn lines [file-reader]
    (line-seq file-reader)
)

(defn words-per-line [line]
  (re-seq #"[a-z]+" line)
  )


(defn process-my-file-improved [file-reader]
 (sort-by second > (frequencies (mapcat words-per-line (lines file-reader)))
 )
 )


;(my-main "/home/catonano/Berlino/stammtisch/moby-dic-originale.txt")
;(-main "/home/catonano/Berlino/stammtisch/moby-dic-originale.txt")
(defn my-main [path-to-the-file]
   (with-open [mio-file (clojure.java.io/reader path-to-the-file)]
      (process-my-file-improved mio-file)
   ) 
)

(defn print-it [path-to-file]
  (let [to-be-printed (take 10 (my-main path-to-file))]
    (dorun (map #(println %) to-be-printed ))  )
  )


(defn main* [path-to-file]
(print-it path-to-file)
)


(defn -main [path-to-file & args]
  (case (first args)
    ; The -b option does a benchmark. This implies head retention.
    ; There's no other way: we cannot reprocess standard input.
    ("-b" "--bench")
      (cr/quick-bench (main* path-to-file) :verbose)
      (main* path-to-file)
  )
)
