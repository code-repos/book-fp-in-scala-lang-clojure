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


(defn product [doubles]
  (cond (empty? doubles) 1.0
        (zero? (.head doubles)) 0.0
        :else (* (.head doubles) (product (.tail doubles)))))

(assert (=   3.0 (product (Cons 3.0 nil)))                                             "product of single-element list should be the element")
(assert (= 120.0 (product (Cons 1.0 (Cons 2.0 (Cons 3.0 (Cons 4.0 (Cons 5.0 nil))))))) "product of list should be product of its elements")

; Exercise 3.2
; Implement the function tail for removing the first element of a List. Note that the
; function takes constant time. What are different choices you could make in your
; implementation if the List is Nil? We’ll return to this question in the next chapter.

(defn tail [list]
  (if (empty? list)
    (throw (IllegalArgumentException. "Empty list has no tail!"))
    (.tail list)))

(assert (= (Cons 2 (Cons 3 nil)) (tail (Cons 1 (Cons 2 (Cons 3 nil))))) "the tail of a list is the list without its head")
