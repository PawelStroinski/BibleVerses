(ns BibleVerses.report
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(defn Verses-files []
  (let [all-files (file-seq (io/file "."))
        file-filter #(re-matches #"Verses\d+-\d+"
          (.getName %))]
    (filter file-filter all-files)))

(defn merge-Verses-files [Verses-files]
  (let [read-file #(with-open [reader (io/reader %)]
          (read-string
            (str/join "\n"
              (line-seq reader))))]
    (apply merge
      (map read-file Verses-files))))

(defn order-Verses [Verses]
  (sort-by second > Verses))

(defn format-Verses [Verses]
  (let [formatted-Verses (map
    (fn [[Verse total]]
      (str Verse "\n" total))
    Verses)]
  (str/join "\n\n" formatted-Verses)))

(defn -main []
  (let [formatted-Verses (format-Verses
          (order-Verses
            (merge-Verses-files
              (Verses-files))))
        filename "BibleVerses.txt"
        writer (io/writer filename)]
    (.write writer formatted-Verses)
    (.close writer)
    (println "File" filename "has been created.")
    (println formatted-Verses)))