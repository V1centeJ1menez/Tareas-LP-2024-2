#lang scheme

(define (profundidades arbol)
  (define (profundidad-aux nodo nivel)
    (cond ((null? nodo) '())
          ((eq? (car nodo) 'T) (cons nivel (profundidad-aux (cdr nodo) nivel)))
          ((list? (car nodo))
           (append (profundidad-aux (car nodo) (+ nivel 1))
                   (profundidad-aux (cdr nodo) nivel)))
          (else (profundidad-aux (cdr nodo) nivel))))
  (sort (profundidad-aux arbol 0) <))

;; Ejemplos de prueba:
(profundidades '(1 (T (2 (T) (3))) (4 (5) (6)) (7 (T (8)))))
;; Resultado esperado: (1 2 2 3)

(profundidades '(1 (6 (3) (2 (5))) (4 (7 (8) (9)))))
;; Resultado esperado: ()

(profundidades '())
;; Resultado esperado: ()
