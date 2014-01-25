(ns homework.core)

(defn find-node [predicate tree]
    (if (predicate tree)
      tree
      (when tree
        (first 
          (filter
            #(not (nil? %))
              (map #(find-node predicate %) (:children tree)))))))

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

(defn find-node-with-path [path-so-far path-predicate get-children tree])
;CONTRACT
;if it's in the subree
  ;return it
;if it's not in the subtee
  ;return nil

;IDEA:
; if predicate tree
;  tree
;  else
; get the children of the tree and iterate through them, checking each one
