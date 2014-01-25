(ns homework.core)

(defn find-node [predicate tree]
    (if (predicate tree)
      tree
      (when tree
        (first 
          (filter
            #(not (nil? %))
              (map #(find-node predicate %) (:children tree)))))))

(defn find-all [predicate tree]
  #{})

(defn find-node-custom [predicate get-children tree]
  (if (predicate tree)
    tree
  (let [children (get-children tree)]
    (when children
     (first
       (filter 
       #(not (nil? %))
       (map
         (fn [tree] 
           (find-node-custom predicate get-children tree))
         children)))))))

(defn find-node-with-path [path-so-far path-predicate get-children tree]
  (if (path-predicate path-so-far)
    (last path-so-far) 
      (let [children (get-children tree)
            path (conj path-so-far tree)]
        (when children
          (first
            (filter 
              #(not (nil? %))
              (map
                (fn [tree] 
                  (find-node-with-path path path-predicate get-children tree))
                children)))))))
