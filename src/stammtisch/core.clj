(ns stammtisch.core
  (:gen-class)
  (:require clojure.java.io
  [criterium.core :as cr])
  )



(defn print-it [ ]
  (let [to-be-printed (take 10 (process-my-file))]
    (dorun (map #(println %) to-be-printed ))  )
  )


(defn main* []
(print-it)
)


(defn -main [& args]
  (case (first args)
    ; The -b option does a benchmark. This implies head retention.
    ; There's no other way: we cannot reprocess standard input.
    ("-b" "--bench")
      (cr/quick-bench (main*) :verbose)
      (main*)
  )
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

(defn my-main []
   (with-open [mio-file (clojure.java.io/reader "/home/catonano/Berlino/stammtisch/moby-dic-originale.txt")]
      (process-my-file-improved mio-file)
   ) 
)
