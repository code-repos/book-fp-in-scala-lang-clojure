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
  (cond (= nil doubles) 1.0
        (= 0.0 (.head doubles)) 0.0
        :else (* (.head doubles) (product (.tail doubles)))))

(assert (=   3.0 (product (Cons 3.0 nil)))                                             "product of single-element list should be the element")
(assert (= 120.0 (product (Cons 1.0 (Cons 2.0 (Cons 3.0 (Cons 4.0 (Cons 5.0 nil))))))) "product of list should be product of its elements")

; Exercise 3.2
; Implement the function tail for removing the first element of a List. Note that the
; function takes constant time. What are different choices you could make in your
; implementation if the List is Nil? We’ll return to this question in the next chapter.

(defn tail [list]
  (if (= nil list)
    (throw (IllegalArgumentException. "Empty list has no tail!"))
    (.tail list)))

(assert (= (Cons 2 (Cons 3 nil)) (tail (Cons 1 (Cons 2 (Cons 3 nil))))) "the tail of a list is the list without its head")

; EXERCISE 3.3
; Using the same idea, implement the function setHead for replacing the first element
; of a List with a different value.

(defn set-head [list x]
  (if (= nil list)
    (throw (IllegalArgumentException. "Empty list has no head!"))
    (Cons x (.tail list))))

(assert (=                   (Cons 0 nil) (set-head (Cons 1 nil) 0))                   "can change the head of a singleton list")
(assert (= (Cons 0 (Cons 2 (Cons 3 nil))) (set-head (Cons 1 (Cons 2 (Cons 3 nil))) 0)) "can change the head of a list")

; EXERCISE 3.4
; Generalize tail to the function drop, which removes the first n elements from a list.
; Note that this function takes time proportional only to the number of elements being
; dropped—we don’t need to make a copy of the entire List.

(defn drop [list  n]
  (cond (= n 0) list
        (= nil list) (throw (IllegalArgumentException. "Cannot drop elements from empty list!"))
        :else (drop (.tail list) (dec n))))

(assert (= (Cons 1 (Cons 2 (Cons 3 nil))) (drop (Cons 1 (Cons 2 (Cons 3 nil))) 0)) "can drop zero element from list")
(assert (=          (Cons 2 (Cons 3 nil)) (drop (Cons 1 (Cons 2 (Cons 3 nil))) 1)) "can drop one element from list")
(assert (=                   (Cons 3 nil) (drop (Cons 1 (Cons 2 (Cons 3 nil))) 2)) "can drop two element from list")
(assert (=                            nil (drop (Cons 1 (Cons 2 (Cons 3 nil))) 3)) "can drop all elements from list")

; EXERCISE 3.5
; Implement dropWhile, which removes elements from the List prefix as long as they
; match a predicate.
(defn dropwhile [l f]
  (cond (empty? l) l
        (f (.head l)) (dropwhile (.tail l) f)
        :else l))

(assert (=                                      nil (dropwhile nil even?))                                      "no element to drop")
(assert (=  (Cons 1 (Cons 2 (Cons 3 (Cons 4 nil)))) (dropwhile (Cons 1 (Cons 2 (Cons 3 (Cons 4 nil)))) even?))  "no elements are dropped")
(assert (=           (Cons 3 (Cons 4 (Cons 5 nil))) (dropwhile (Cons 2 (Cons 3 (Cons 4 (Cons 5 nil)))) even?))  "first element is dropped")
(assert (=                                      nil (dropwhile (Cons 2 (Cons 4 (Cons 6 (Cons 8 nil)))) even?))  "all elements are dropped")