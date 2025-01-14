#lang scheme

;; buscador : lista elemento -> índice
;; Esta función busca un elemento en una lista y retorna su índice.
;; Si el elemento no se encuentra, retorna -1.
;;
;; lista : La lista en la que se busca el elemento.
;; elemento : El elemento que se desea buscar en la lista.

(define (buscador lista elemento)
  ;; aux : lst elem index -> índice
  ;; Esta función auxiliar recorre la lista buscando el elemento.
  ;; lst : la lista restante a procesar.
  ;; elem : el elemento que se está buscando.
  ;; index : el índice actual (comienza en 1).
  (define (aux lst elem index)
    (cond
      ((null? lst) -1)                           ;; Si la lista está vacía, retornar -1.
      ((equal? (car lst) elem) index)            ;; Si encontramos el elemento, retornar el índice.
      (else (aux (cdr lst) elem (+ index 1)))))  ;; De lo contrario, continuar con el resto de la lista.
  
  ;; Llamada inicial a la función auxiliar con el índice comenzando en 1.
  (aux lista elemento 1))                        