(ns soundcoding.core
  (:use clj-fuzzy.phonetics)
  (:require [clojure.string :as str])
  (:gen-class))

(def mapalphas (hash-map "B" 1, "F" 1, "P" 1, "V" 1,
                 "C" 2, "G" 2, "J" 2, "K" 2, "Q" 2, "S" 2, "X" 2, "Z" 2,
                 "D" 3, "T" 3,
                 "L" 4, 
                 "M" 5, "N" 5,
                 "R" 6, 
                 "A" ".", "E" ".", "H" ".", "I" ".", "O" ".", "U" ".", "W" ".",
                 "Y" "."))

(defn get-code [c]  
  (case c                    
    (\B \F \P \V) 1    
    (\C \G \J \K     
      \Q \S \X \Z) 2    
    (\D \T) 3    
    \L 4    
    (\M \N) 5    
    \R 6    
    nil)) ;(\A \E \I \O \U \H \W \Y)

(defn reduction [x next]
  (let [nextv (get-code next)]
    (if (and (not= nextv (last x))
           (not(nil? nextv)))
      (conj x nextv)
      x)))
    
    
  
  
    
  
  

(defn code[word]
  (let [[first & rest] (.toUpperCase word)
        next (get-code(first rest))]
    (if (nil? next)
      (recur(apply str first(alls rest)))
      (let [nums (reduce reduction[] rest)]
        (apply str first (take 3 (conj nums 0 0 0)))))))
  
 ;; (if (str/blank? word)
  ;;  (do (println "Empty String"
  ;;  true))
  ;;  (do (count word)
   ;; (str/upper-case word)
  ;;  (println word)
   ;; false))
  
    
      
  

(defn -main []
  ;;(soundex "Ashcroft")
  (code "Ashcroft"))
