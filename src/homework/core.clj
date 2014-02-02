(ns homework.core)

(defn find-node 
  ([predicate tree]
  (find-node predicate :children tree))

  ([predicate get-children tree]
  (if (predicate tree)
    tree
  (let [children (get-children tree)]
    (when children
     (first
       (filter 
       #(not (nil? %))
       (map
         (fn [tree] 
           (find-node predicate get-children tree))
         children))))))))

(defn find-all [predicate tree]
  (reduce
    (fn [matches potential-match]
      (let [children (:children potential-match)]
        (if (predicate potential-match)
          (into matches potential-match)
          (find-all predicate children))))
      #{}
      (if (vector? tree) tree [tree])))

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
