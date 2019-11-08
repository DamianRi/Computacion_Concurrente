## Práctica 4
---
#### Alexis Hernandez Castro
#### Rivera González Damián
---
+ Argumenta por qué tu solución cumple con la propiedad de exclusión mutua.
  
  Porque usamos un candado para controlar el acceso a las variables compartidas, por lo cual aseguramos que únicamente un hilo hará la escritura sobre dichas variables.
  Para la parte de la exclusión mutua en el uso de los baños por genero, se debe a que hacemos esperar cada genero (hombre o mujer) hasta que el último de del genero opuesto sale del baño, y si hay del genero opuesto esperando se le da el aviso de que ahora puede pasar, mientras que no haya del genero opuesto en espera se sigue usando por un mismo genero.

+ Argumenta por qué tu solución cumple con la propiedad de libre inanición.
  
  Para los hilos de un genero se les hace usar el baño mientras que haya uno del mismo genero, y cuando hay uno del genero opuesto esperando y el úlitmo del genero actual sale, les da paso a los del otro genero. De manera que se les da paso a todos los hilos que quieran usar siempre que su genero este usando el baño, y como en algún momento termina de usarse por un genero da aviso al otro genero y todos pueden entrar al baño.