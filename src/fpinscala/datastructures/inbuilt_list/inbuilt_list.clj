 (ns inbuilt_list)

 (assert (=               (list)     '() )  "the empty list is '()")
 (assert (=     (first (list 3))       3 )  "the head of a single-element list it the element")
 (assert (=      (rest (list 3))     '() )  "the tail of a single-element list is '() ")
 (assert (= (first (list 1 2 3))       1 )  "the head of an n-element list it the 1st element")
 (assert (=  (rest (list 1 2 3))  '(2 3) )  "the tail of an n-element list is the remaining n-1 elements")

 (defn sum [ints]
   (if (empty? ints)
     0
     (+ (first ints)
        (sum (rest ints)))))

 (assert (=  0 (sum '()))          "sum of empty list should be 0")
 (assert (=  3 (sum '(3)))         "sum of single-element list should be the element")
 (assert (= 15 (sum '(1 2 3 4 5))) "sum of list should be sum of its elements")
