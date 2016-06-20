(ns list)

(defrecord List [head tail])
(defn Cons [head tail] (->List head tail))

(assert (=                     1  (.head (Cons 1 nil)))                    "the head of a single-element list it the element")
(assert (=                   nil  (.tail (Cons 1 nil)))                    "the tail of a single-element list is nil ")
(assert (=                     1  (.head (Cons 1 (Cons 2 (Cons 3 nil)))))  "the head of an n-element list it the 1st element")
(assert (= (Cons 2 (Cons 3 nil))  (.tail (Cons 1 (Cons 2 (Cons 3 nil)))))  "the tail of an n-element list is the remaining n-1 elements")

(defn sum [ints]
  (if (= nil ints)
    0
    (+ (.head ints)
       (sum (.tail ints)))))

(assert (=  0 (sum nil))                                              "sum of empty list should be 0")
(assert (=  3 (sum (Cons 3 nil)))                                     "sum of single-element list should be the element")
(assert (= 15 (sum (Cons 1 (Cons 2 (Cons 3 (Cons 4 (Cons 5 nil))))))) "sum of list should be sum of its elements")
