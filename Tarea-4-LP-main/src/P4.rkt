#lang scheme

;; Función principal para calcular las profundidades donde se encuentran los nodos 'T' en un árbol
;; representado como listas anidadas.
;; profundidades : lista -> lista
;;
;; arbol : Lista que representa el árbol donde se busca el nodo 'T' y se registran sus niveles.

(define (profundidades arbol)
  
  ;; Función auxiliar que recorre el árbol y acumula los niveles donde se encuentra 'T'
  ;; profundidad-aux : lista -> entero -> lista
  ;;
  ;; nodo : Lista o sublista actual que se está explorando en busca de 'T'.
  ;; nivel : Entero que representa la profundidad actual en el árbol.
  (define (profundidad-aux nodo nivel)
    (cond ((null? nodo) '()) ; Caso base: si el nodo es vacío, devuelve una lista vacía.
          
          ;; Caso: si el primer elemento del nodo es 'T', añade el nivel actual a la lista
          ;; de profundidades y continúa explorando el resto del nodo al mismo nivel.
          ((eq? (car nodo) 'T)
           (cons nivel (profundidad-aux (cdr nodo) nivel)))
          
          ;; Caso: si el primer elemento es una lista (un subárbol), llama recursivamente
          ;; a profundidad-aux para explorar el subárbol, incrementando el nivel en 1.
          ;; Luego, llama recursivamente en el resto del nodo al mismo nivel.
          ((list? (car nodo))
           (append (profundidad-aux (car nodo) (+ nivel 1))
                   (profundidad-aux (cdr nodo) nivel)))
          
          ;; Caso: si el primer elemento no es 'T' ni una lista, continúa con el resto del nodo
          ;; sin cambiar el nivel.
          (else (profundidad-aux (cdr nodo) nivel))))
  
  ;; Llama a la función auxiliar en el árbol dado, empezando desde el nivel 0, y
  ;; ordena la lista resultante de profundidades en orden ascendente para una salida organizada.
  (sort (profundidad-aux arbol 0) <))

;; Ejemplos de prueba:
(profundidades '(1 (T (2 (T) (3))) (4 (5) (T)) (7 (T (8)))))
;; Resultado esperado: (1 2 2 3)

(profundidades '(1 (6 (3) (2 (5))) (4 (7 (8) (9)))))
;; Resultado esperado: ()

(profundidades '())
;; Resultado esperado: ()
