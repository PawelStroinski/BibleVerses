(ns BibleVerses.query
  (:require [clojure.string :as str]
            [clojure.java.io :as io]
            [clj-http.client :as client]
            [clojure.data.codec.base64 :as base64]
            [cemerick.url :refer (url-encode)])
  (:use clj-xpath.core))

(def bingSearchAPIAccountKey "<cut>")

(def bingSearchAPIAccountKeyEncoded
  (->> (str bingSearchAPIAccountKey ":" bingSearchAPIAccountKey)
       (.getBytes)
       (base64/encode)
       (map char)
       (apply str)))

(defn get-Verses [start stop]
  (->> "KJV.xml"
       (java.io.FileInputStream.)
       (xml->doc)
       ($x "//VERS")
       (map #($x:text "." %))
       (distinct)
       (take (read-string stop))
       (drop (dec (read-string start)))))

(defn get-url [Verse]
  (str "https://api.datamarket.azure.com/Bing/Search/v1/Composite?Sources=%27web%27&Query=%27%22"
       (-> (url-encode Verse)
           (str/replace "%27" "''"))
       "%22%27&Options=%27DisableLocationDetection%27&WebSearchOptions=%27DisableHostCollapsing%2BDisableQueryAlterations%27"))

(defn get-total [Verse]
  (let [url (get-url Verse)
        headers {"Authorization" (str "Basic " bingSearchAPIAccountKeyEncoded)}
        response (client/get url {:headers headers})
        document (xml->doc (:body response))
        total ($x:text "//*[local-name() = 'WebTotal']" document)]
    (read-string total)))

(defn get-Verses-with-total [start stop]
  (->> (get-Verses start stop)
       (map (fn [Verse] {Verse (get-total Verse)}))
       (apply merge)))

(defn -main [start stop]
  (let [Verses (str (get-Verses-with-total start stop))
        filename (str "Verses" start "-" stop)
        writer (io/writer filename)]
    (.write writer Verses)
    (.close writer)
    (println "File" filename "has been created.")
    (println Verses)))