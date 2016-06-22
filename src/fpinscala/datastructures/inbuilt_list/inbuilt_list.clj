 (ns inbuilt_list)

 (assert (=    '()               (list) )  "the empty list is '()")
 (assert (=      3     (first (list 3)) )  "the head of a single-element list it the element")
 (assert (=    '()      (rest (list 3)) )  "the tail of a single-element list is '() ")
 (assert (=      1 (first (list 1 2 3)) )  "the head of an n-element list it the 1st element")
 (assert (= '(2 3)  (rest (list 1 2 3)) )  "the tail of an n-element list is the remaining n-1 elements")

 (assert (=      3                   (first (cons 3 '())) )  "the head of a single-element list it the element")
 (assert (=    '()                    (rest (cons 3 '())) )  "the tail of a single-element list is '() ")
 (assert (=      1 (first (cons 1 (cons 2 (cons 1 '())))) )  "the head of an n-element list it the 1st element")
 (assert (= '(2 3)  (rest (cons 1 (cons 2 (cons 3 '())))) )  "the tail of an n-element list is the remaining n-1 elements")

 (defn sum [[n & ns :as ints]]
   (if (empty? ints)
     0
     (+ n (sum ns))))

(assert (=  0 (sum '()))          "sum of empty list should be 0")
(assert (=  3 (sum '(3)))         "sum of single-element list should be the element")
(assert (= 15 (sum '(1 2 3 4 5))) "sum of list should be sum of its elements")

(assert (=  0 (sum '()))                                              "sum of empty list should be 0")
(assert (=  3 (sum (cons 3 '())))                                     "sum of single-element list should be the element")
(assert (= 15 (sum (cons 1 (cons 2 (cons 3 (cons 4 (cons 5 '()))))))) "sum of list should be sum of its elements")

(defn product [[x & xs :as ds]]
  (cond (empty? ds) 1.0
        (zero? x) 0.0
        :else (* x (product xs))))

(assert (=    3.0 (product '(3.0)))                  "product of single-element list should be the element")
(assert (=  120.0 (product '(1.0 2.0 3.0 4.0 5.0)))  "product of list should be product of its elements")

; Exercise 3.2
; Implement the function tail for removing the first element of a List. Note that the
; function takes constant time. What are different choices you could make in your
; implementation if the List is Nil? We’ll return to this question in the next chapter.

 (defn tail [[x & xs :as list]]
   (if (empty? list)
     (throw (IllegalArgumentException. "Empty list has no tail!"))
     xs))

(assert (= '(2.0 3.0 4.0 5.0) (tail '(1.0 2.0 3.0 4.0 5.0)))  "the tail of a list is the list without its head")

; EXERCISE 3.3
; Using the same idea, implement the function setHead for replacing the first element
; of a List with a different value.

(defn set-head [list x]
  (if (empty? list)
    (throw (IllegalArgumentException. "Empty list has no head!"))
    (cons x (tail list))))

(assert (=                   (cons 0 nil) (set-head (cons 1 nil) 0))                   "can change the head of a singleton list")
(assert (= (cons 0 (cons 2 (cons 3 nil))) (set-head (cons 1 (cons 2 (cons 3 nil))) 0)) "can change the head of a list")
