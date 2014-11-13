(defproject BibleVerses "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [com.github.kyleburton/clj-xpath "1.4.3"]
                 [clj-http "1.0.0"]
                 [org.clojure/data.codec "0.1.0"]
                 [com.cemerick/url "0.1.1"]]
  :main ^:skip-aot BibleVerses.core
  :aliases {"query" ["run" "-m" "BibleVerses.query"]
            "report" ["run" "-m" "BibleVerses.report"]}
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})