(ns stammtisch.core
  (:gen-class)
  (:use [stammtisch.meikel-version][stammtisch.common-functions])
  (:require clojure.java.io
  [criterium.core :as cr])
  )


; Usage:
; load this namespace ( (use 'stammtisch.core) ) and then type either one of these lines
; (-main "path/to/the/file.txt" "vanilla")
; (-main "path/to/the/file.txt" "meikel")
; (-main "path/to/the/file.txt" "vanilla" "-b")
; (-main "path/to/the/file.txt" "meikel" "-b")

(defn lines-in-the-file [file-reader]
   (line-seq file-reader)
)

(defn vanilla-words [coll-of-lines]
  (mapcat words-per-line coll-of-lines)
)

(defn vanilla-process-my-file [path-to-the-file]
 (with-open [mio-file (clojure.java.io/reader path-to-the-file)]
   (sort-by second > (frequencies (vanilla-words (lines-in-the-file mio-file)))
   )
 )
)

(defn set-up [version path-to-the-file]
  (case version
  ("vanilla")
     (vanilla-process-my-file path-to-the-file)
  ("meikel")
     (meikel-process-my-file path-to-the-file)   
  )
)

(defn print-it [version path-to-file]
  (let [to-be-printed (take 10 (set-up version path-to-file))]
    (dorun (map #(println %) to-be-printed ))  )
  )

(defn -main [path-to-file version & args]
   (let [benchmarking (first args)]
         (if (= benchmarking "-b")
            (cr/quick-bench (print-it version path-to-file) :verbose)
            (print-it version path-to-file)
         )
   )
)
