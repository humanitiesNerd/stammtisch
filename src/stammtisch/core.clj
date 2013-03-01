(ns stammtisch.core
  (:gen-class)
  (:require clojure.java.io
  [criterium.core :as cr])
  )

(defn frequencies-per-line [line ]
  (frequencies (re-seq #"[a-z]+" line))
  )

(defn process-my-file [ ]
  (with-open [mio-file (clojure.java.io/reader "/home/catonano/Berlino/stammtisch/moby-dic.txt")]
   (sort-by second > (reduce (partial merge-with +) (map frequencies-per-line (line-seq mio-file)))
    )
  )
  )

(defn print-it [ ]
  (let [to-be-printed (take 10 (process-my-file))]
    (dorun (map #(println %) to-be-printed ))  )
  )


(defn main* [ ]
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


(defn lines [ ]
  (with-open [mio-file (clojure.java.io/reader "/home/catonano/Berlino/stammtisch/moby-dic.txt") ]
    (line-seq mio-file))
  )

(defn words [line]
  (re-seq #"[a-z]+" line)
  )

(defn process-my-file-improved [ ]
 (sort-by second > (frequencies (mapcat words (lines)))
 )
 )
